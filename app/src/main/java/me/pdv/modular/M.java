package me.pdv.modular;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by pdv on 1/28/17.
 */

public class M {

    interface Map<K, V> extends Func1<K, V> {}

    interface Source<T> {
        O<T> get();
    }

    interface Sink<T> {
        void put(T t);
    }

}
