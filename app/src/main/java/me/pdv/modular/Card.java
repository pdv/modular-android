package me.pdv.modular;

import android.widget.TextView;

import rx.Observable;

/**
 * Created by pdv on 1/1/17.
 */

public class Card {

    private TextView titleTextView;
    private TextView descriptionTextView;

    public Card(Observable<String> title, Observable<String> description) {
        title.subscribe(titleTextView::setText);
        description.subscribe(descriptionTextView::setText);
    }
}
