package me.pdv.modular;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pdv on 2/18/2017.
 */

public class AddEditGameActivity extends Activity {

    interface DropDownTextView<T> {
        Observable<T> selections(Observable<List<T>> options);
        void setText(String text);
    }

    interface Repository<T> {
        Observable<T> monitor(String id);
        Single<T> get(String id);
        Completable forceRefresh();
        Completable save(T t);
        Completable update(String id, Action1<T> modifyBlock);
    }

    interface Injector<T> {
        T inject();
    }

    interface Team {
        String getName();
        List<String> getSchedule();
    }

    interface Game {
        Date getStartDate();
    }

    static class Schedule {

        interface View {
            void showSchedule(List<Game> schedule);
        }

        interface User {
            Observable<Date> rangeStart();
            Observable<Date> rangeEnd();
        }

        private Repository<Team> teamRepository;
        private Repository<Game> gameRepository;
        private User user;
        private View view;
        private CompositeSubscription subs;

        Schedule(Injector<Repository<Team>> teamRepositoryInjector,
                 Injector<Repository<Game>> gameRepositoryInjector) {
            teamRepository = teamRepositoryInjector.inject();
            gameRepository = gameRepositoryInjector.inject();
        }

        public void setView(View view) {
            this.view = view;
        }

        public void setUser(User user) {
            this.user = user;
        }

        static abstract class Maybe<T> {

            abstract boolean hasValue();
            abstract T getThrowing() throws NoSuchElementException;
            abstract T getOrNull();
            abstract void applyMaybe(@NonNull Action1<? super T> action);

            static <T> Maybe<T> Just(T t) {
                return t == null ? new Nothing() : new Just<>(t);
            }

            static Maybe Nothing() {
                return new Nothing();
            }

            static class Just<T> extends Maybe<T> {
                @NonNull T x;
                private Just(@NonNull T x) {
                    this.x = x;
                }
            }

            static class Nothing extends Maybe {
                private Nothing() {
                }
            }

        }

        private static <T, U> Observable.Transformer<T, T> filterWithLatestFrom(
            Observable<U> filterObs,
            Func2<T, U, Boolean> predicate
        ) {
            return valueObs -> valueObs
                    .withLatestFrom(filterObs, (value, filterValue) -> Pair.create(value, predicate.call(value, filterValue)))
                    .filter(Pair::second)
                    .map(Maybe::value);
        }

        private static <T1, T2> Func2<T1, T2, Boolean> complement(Func2<T1, T2, Boolean> func) {
            return (t1, t2) -> !func.call(t1, t2);
        }

        private static Func2<Game, Date, Boolean> before = (game, bound) -> game.getStartDate().compareTo(bound) == 1;
        private static Func2<Game, Date, Boolean> after = complement(before);

        public void showGamesBetweenDatesForTeamWithId(String teamId) {
            Subscription subsription = teamRepository.monitor(teamId)
                    .map(Team::getSchedule)
                    .flatMap(gameIds -> Observable.from(gameIds)
                            .flatMap(gameId -> gameRepository.get(gameId).toObservable())
                            .compose(filterWithLatestFrom(user.rangeStart(), after))
                            .compose(filterWithLatestFrom(user.rangeEnd(), before))
                            .toList())
                    .subscribe(view::showSchedule);
            subs.add(subsription);
        }

        public void tearDown() {
            subs.clear();
        }


    }

}
