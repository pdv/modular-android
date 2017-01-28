package me.pdv.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.Notification;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private Observable<Team> getTeams() {
        return Observable.just(null, null, null);
    }
}
