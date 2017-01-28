package me.pdv.modular;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * Created by pdv on 1/28/17.
 */

public class RxButton extends Button {

    public ConnectableObservable<?> clicks;

    public RxButton(Context context) {
        this(context, null);
    }

    public RxButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        clicks = Observable.create(subscriber -> setOnClickListener(__ -> subscriber.onNext(null))).publish();
    }

}
