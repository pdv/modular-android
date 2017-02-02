package me.pdv.modular;

import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by pdv on 2/1/17.
 */

public class CounterTests {

    @Test
    public void testStartAtZero() {
        Observable<Void> upClicks = Observable.empty();
        Observable<Void> downClicks = Observable.empty();
        TestSubscriber<Integer> count = TestSubscriber.create();

        Counter.count(upClicks, downClicks).subscribe(count);

        count.assertValue(0);
    }

    @Test
    public void testIncrement() {
        Observable<Void> upClicks = Observable.just(null);
        Observable<Void> downClicks = Observable.empty();
        TestSubscriber<Integer> count = TestSubscriber.create();

        Counter.count(upClicks, downClicks).subscribe(count);

        count.assertValues(0, 1);
    }

    @Test
    public void testMultipleIncrement() {
        Observable<Void> upClicks = Observable.just(null, null, null);
        Observable<Void> downClicks = Observable.empty();
        TestSubscriber<Integer> count = TestSubscriber.create();

        Counter.count(upClicks, downClicks).subscribe(count);

        count.assertValues(0, 1, 2, 3);
    }

    @Test
    public void testDecrement() {
        Observable<Void> upClicks = Observable.empty();
        Observable<Void> downClicks = Observable.just(null);
        TestSubscriber<Integer> count = TestSubscriber.create();

        Counter.count(upClicks, downClicks).subscribe(count);

        count.assertValues(0, -1);
    }

    @Test
    public void testBoth() {
        Observable<Void> upClicks = Observable.just(null);
        Observable<Void> downClicks = Observable.just(null);
        TestSubscriber<Integer> count = TestSubscriber.create();

        Counter.count(upClicks, downClicks).subscribe(count);

        count.assertValues(0, 1, 0);
    }

    @Test
    public void testBothMultiple() {
        Observable<Void> downClicks = Observable.just(null, null, null);
        Observable<Void> upClicks = downClicks.toCompletable().andThen(Observable.just(null, null, null));
        TestSubscriber<Integer> count = TestSubscriber.create();

        Counter.count(upClicks, downClicks).subscribe(count);

        count.assertValues(0, -1, -2, -3, -2, -1, 0);
    }
}
