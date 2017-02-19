package me.pdv.modular;

import android.support.annotation.NonNull;

import rx.functions.Func2;

/**
 * Created by pdv on 2/19/2017.
 */

public final class F {

    @NonNull
    public static <T1, T2> Func2<T1, T2, Boolean> complement(@NonNull Func2<T1, T2, Boolean> func) {
        return (t1, t2) -> !func.call(t1, t2);
    }

}
