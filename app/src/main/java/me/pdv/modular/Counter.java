package me.pdv.modular;

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

