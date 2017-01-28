package me.pdv.modular;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by pdv on 1/28/17.
 */

public class SearchTests {

    @Test
    public void testSearchResults() {
        List<Team> repoResults = Arrays.asList(new Team("Houston", "Rebels"));
        Search.Repository repository = __ -> Observable.just(repoResults);
        Search.QueryView queryView = () -> Observable.just("query");
        TestSubscriber<List<Team>> results = TestSubscriber.create();
        Search.ResultsView resultsView = results::onNext;
        Search.ErrorHandler handler = null;

        Search.displaySearchResults(repository, queryView, resultsView, handler);
        results.assertValue(repoResults);
    }

}
