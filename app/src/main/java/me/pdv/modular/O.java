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


    public class Parallel extends ArrayList<O> {


    }

}
