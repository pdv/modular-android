package me.pdv.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just(1, 2, 3)
                .filter(x -> x > 2)
                .subscribe(System.out::println);
    }
}
