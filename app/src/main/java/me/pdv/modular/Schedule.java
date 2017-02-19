package me.pdv.modular;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pdv on 2/2/17.
 */

class Schedule {

    interface View {
        void showSchedule(List<Game> schedule);
    }

    interface User {
        Observable<Date> rangeStart();
        Observable<Date> rangeEnd();
    }

    interface Game {
        Date getStartDate();
    }

    interface Team {
        List<String> getSchedule();
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

    private static Func2<Game, Date, Boolean> before = (game, bound) -> game.getStartDate().compareTo(bound) == 1;
    private static Func2<Game, Date, Boolean> after = F.complement(before);

    public void showGamesBetweenDatesForTeamWithId(String teamId) {
        Subscription subsription = teamRepository.monitor(teamId)
                .map(Team::getSchedule)
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
