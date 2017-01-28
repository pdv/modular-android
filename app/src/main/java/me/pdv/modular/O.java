package me.pdv.modular;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;

/**
 * Created by pdv on 1/1/17.
 */

public class O<T> extends Observable<T> {

    protected O(OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }

    public static void newInstance() {
        return
    }

    public Parallel split(int channels) {
        ConnectableObservable<T> connectableObservable = this.publish();
        Parallel parallel = new Parallel();
        for (int i = 0; i < channels; i++) {
            connectableObservable.subscribe()
            Observable<T> obs = Subscriber
            O<T> channel =
            parallel.add()
        }

    }

    public class Parallel extends ArrayList<O> {


    }

}
