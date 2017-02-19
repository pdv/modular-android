package me.pdv.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView countView = (TextView) findViewById(R.id.tv_count);
        Button upButton = (Button) findViewById(R.id.btn_up);
        Button downButton = (Button) findViewById(R.id.btn_down);
        // Counter.count(RxView.clicks(upButton), RxView.clicks(downButton), countView::setText);

        View view = new View(this);

        Observable.merge(
                RxView.clicks(upButton).map(__ -> 1),
                RxView.clicks(downButton).map(__ -> -1))
                .scan(0, MathUtil::sum)
                .map(String::valueOf)
                .subscribe(countView::setText);

        RxView.clicks(view)
                .scan(0, (count, __) -> count + 1)
                .map(String::valueOf)
                .subscribe(countView::setText);

        Observable<Long> upTicks = Observable.interval(5, TimeUnit.SECONDS);
        Observable<?> downTicks = Observable.timer(10, TimeUnit.MINUTES);
        Counter.count(upTicks, downTicks)
                .compose(Counter::format)
                .map(String::toUpperCase)
                .map(Voice::textToSpeech)
                .subscribe(this::playSound);
    }

    private void playSound(Sound sound) {

    }

    private static class Sound {

    }

    private static class Voice {
        static Sound textToSpeech(String text) {
            return null;
        }
    }

}
