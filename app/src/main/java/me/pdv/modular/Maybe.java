package me.pdv.modular;

import android.support.annotation.NonNull;

import java.util.NoSuchElementException;

import rx.functions.Action1;

/**
 * Created by pdv on 2/18/2017.
 */

public abstract class Maybe<T> {

    abstract boolean hasValue();
    abstract T getThrowing() throws NoSuchElementException;
    abstract T getOrNull();
    abstract void applyMaybe(@NonNull Action1<? super T> action);

    public static <T> Maybe<T> Just(T t) {
        return t == null ? new Nothing() : new Just<>(t);
    }

    public static Maybe Nothing() {
        return new Nothing();
    }

    public static <T> Maybe<T> ifJustElseNothing(@NonNull T t, boolean cond) {
        return cond ? Just(t) : Nothing();
    }

    private static class Just<T> extends Maybe<T> {

        @NonNull T x;

        private Just(@NonNull T x) {
            this.x = x;
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public T getThrowing() throws NoSuchElementException {
            return x;
        }

        @Override
        public T getOrNull() {
            return x;
        }

        @Override
        public void applyMaybe(@NonNull Action1<? super T> action) {
            action.call(x);
        }

    }

    private static class Nothing extends Maybe {

        private Nothing() {
        }

        @Override
        public boolean hasValue() {
            return false;
        }

        @Override
        public Object getThrowing() throws NoSuchElementException {
            throw new NoSuchElementException("Nothing has no value");
        }

        @Override
        public Object getOrNull() {
            return null;
        }

        @Override
        public void applyMaybe(@NonNull Action1 action) {
            // do nothing
        }

    }

}
