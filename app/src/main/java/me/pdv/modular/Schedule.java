package me.pdv.modular;

import android.widget.DatePicker;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by pdv on 2/2/17.
 */

final interface Schedule {

    interface User {
    }

    interface Game {
        Date getStartDate();
        List<User> getRsvps();
    }

    Observable<List<Integer>> countRsvps(Observable<Date> start,
                                         Observable<Date> end,
                                         Observable<Game> games);

}
