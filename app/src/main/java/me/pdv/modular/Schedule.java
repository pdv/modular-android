package me.pdv.modular;

import android.support.v4.util.Pair;
import android.widget.DatePicker;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pdv on 2/2/17.
 */

class Schedule {

    interface View {
        void showSchedule(List<AddEditGameActivity.Game> schedule);
    }

    interface User {
        Observable<Date> rangeStart();
        Observable<Date> rangeEnd();
    }

    private AddEditGameActivity.Repository<AddEditGameActivity.Team> teamRepository;
    private AddEditGameActivity.Repository<AddEditGameActivity.Game> gameRepository;
    private User user;
    private View view;
    private CompositeSubscription subs;

    Schedule(AddEditGameActivity.Injector<AddEditGameActivity.Repository<AddEditGameActivity.Team>> teamRepositoryInjector,
             AddEditGameActivity.Injector<AddEditGameActivity.Repository<AddEditGameActivity.Game>> gameRepositoryInjector) {
        teamRepository = teamRepositoryInjector.inject();
        gameRepository = gameRepositoryInjector.inject();
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static Func2<AddEditGameActivity.Game, Date, Boolean> before = (game, bound) -> game.getStartDate().compareTo(bound) == 1;
    private static Func2<AddEditGameActivity.Game, Date, Boolean> after = F.complement(before);

    public void showGamesBetweenDatesForTeamWithId(String teamId) {
        Subscription subsription = teamRepository.monitor(teamId)
                .map(AddEditGameActivity.Team::getSchedule)
                .flatMap(gameIds -> Observable.from(gameIds)
                        .flatMap(gameId -> gameRepository.get(gameId).toObservable())
                        .compose(Rx.filterWithLatestFrom(user.rangeStart(), after))
                        .compose(Rx.filterWithLatestFrom(user.rangeEnd(), before))
                        .toList())
                .subscribe(view::showSchedule);
        subs.add(subsription);
    }

    public void tearDown() {
        subs.clear();
    }



}
