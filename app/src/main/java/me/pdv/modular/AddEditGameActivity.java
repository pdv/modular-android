package me.pdv.modular;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by pdv on 2/18/2017.
 */

public class AddEditGameActivity extends Activity {

    interface DropDownTextView<T> {
        Observable<T> selections(Observable<List<T>> options);
        void setText(String text);
    }

    interface Team {
        String getName();
        List<String> getSchedule();
    }

    interface Game {
        Date getStartDate();
    }

    static class Schedule {

    }

}
