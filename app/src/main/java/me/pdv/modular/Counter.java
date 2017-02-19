package me.pdv.modular;

import android.util.Log;

import rx.Observable;
import rx.Subscription;

/**
 * Created by pdv on 1/31/2017.
 */

public final class Counter {

    public static int sum(int... ints) {
        int sum = 0;
        for (int i : ints) {
            sum += i;
        }
        return sum;
    }

    public static Subscription count(Observable<Void> upClicks,
                                     Observable<Void> downClicks,
                                     M.Sink<String> count) {
        return Observable.merge(
                upClicks.map(__ -> 1),
                downClicks.map(__ -> -1))
                .scan(0, Counter::sum)
                .map(String::valueOf)
                .subscribe(count::put);
    }

}
