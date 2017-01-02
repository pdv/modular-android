package me.pdv.modular;

import rx.Observable;

/**
 * Created by pdv on 1/1/17.
 */

public class TeamsProvider {

    public Observable<Team> getTeams() {
        return Observable.just(
                new Team("Downtown", "Cougars"),
                new Team("Fishtown", "Fishes"),
                new Team("Winton", "Winners")
        );
    }

}
