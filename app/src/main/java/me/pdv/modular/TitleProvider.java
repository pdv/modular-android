package me.pdv.modular;

import rx.Observable;

/**
 * Created by pdv on 1/1/17.
 */

public class TitleProvider {

    Observable<String> getTitle(Observable<Team> teams) {
        return teams.map(team -> team.division + " " + team.mascot);
    }

}

