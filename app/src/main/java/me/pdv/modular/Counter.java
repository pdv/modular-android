package me.pdv.modular;

<<<<<<< HEAD
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
=======
import rx.Observable;

/**
 * Created by pdv on 2/1/17.
 */

final class Counter {

    static Observable<Integer> count(Observable<?> upClicks,
                                     Observable<?> downClicks) {
        return Observable.merge(
                upClicks.map(__ -> 1),
                downClicks.map(__ -> -1))
                .scan(0, MathUtil::sum);
    }

    static Observable<String> format(Observable<Integer> count) {
        return count.map(String::valueOf)
                .map(str -> str + " clicks");
    }

}



>>>>>>> 3ff1ee7b0d1c79618d4396dfd3eecd7ac9d7d0c6
