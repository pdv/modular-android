package me.pdv.modular;

import java.util.Arrays;
import java.util.List;

import rx.Observable;

/**
 * Created by pdv on 1/28/17.
 */

public class Repository {

    static List<Team> teams = Arrays.asList(
            new Team("", "Par"),
            new Team("", "Parkdale")
    );

    public Observable<List<Team>> getTeamsForSearch(String search) {
        return Observable.empty();

    }
}
