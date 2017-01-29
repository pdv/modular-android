package me.pdv.modular;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by pdv on 1/28/17.
 */

public final class Search {

    interface Repository extends M.Map<String, Observable<List<Team>>> {}
    interface QueryView extends M.Source<CharSequence> {}
    interface ResultsView extends M.Sink<List<Team>> {}
    interface ErrorHandler extends Action1<Throwable> {}

    public static Subscription displaySearchResults(Repository lookup,
                                                    QueryView queryView,
                                                    ResultsView resultsView,
                                                    ErrorHandler errorHandler) {
        return queryView.get()
                .map(CharSequence::toString)
                .filter(string -> string.length() > 3)
                .flatMap(lookup)
                .doOnError(errorHandler)
                .subscribe(resultsView::put);
    }

    /**
     * Factory
     * Dependency injection
     * Lazy initialization
     * "Singleton"
     * Adapter
     * Bridge
     * Facade
     * "Strategy"
     * Iterator
     * Observer
     *
     */


}
