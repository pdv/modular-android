package me.pdv.modular;

import java.util.Arrays;
import java.util.List;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.functions.Action1;

/**
 * Created by pdv on 1/28/17.
 */

public interface Repository<T> {

    Observable<T> monitor(String id);
    Single<T> get(String id);
    Completable forceRefresh();
    Completable save(T t);
    Completable update(String id, Action1<T> modifyBlock);

}
