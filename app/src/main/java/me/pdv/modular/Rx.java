package me.pdv.modular;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by pdv on 2/19/2017.
 */

public final class Rx {

    @NonNull
    public static <T, U> Observable.Transformer<T, T> filterWithLatestFrom(@NonNull final Observable<U> filterObs,
                                                                           @NonNull final Func2<T, U, Boolean> predicate) {
        return valueObs -> valueObs
                .withLatestFrom(filterObs, (value, filterValue) -> Maybe.ifJustElseNothing(value, predicate.call(value, filterValue)))
                .filter(Maybe::hasValue)
                .map(Maybe::getThrowing);
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> gate(@NonNull Observable<?> gateObs) {
        return source -> source.withLatestFrom(gateObs, F::first);
    }

}
