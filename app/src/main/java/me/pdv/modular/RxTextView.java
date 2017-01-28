package me.pdv.modular;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import rx.functions.Action1;

/**
 * Created by pdv on 1/28/17.
 */

public class RxTextView extends TextView {

    public final Action1<CharSequence> text = this::setText;

    public RxTextView(Context context) {
        this(context, null);
    }

    public RxTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
