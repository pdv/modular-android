package me.pdv.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
=======
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
>>>>>>> 3ff1ee7b0d1c79618d4396dfd3eecd7ac9d7d0c6

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        TextView countView = (TextView) findViewById(R.id.tv_count);
        Button upButton = (Button) findViewById(R.id.btn_up);
        Button downButton = (Button) findViewById(R.id.btn_down);
        Counter.count(RxView.clicks(upButton), RxView.clicks(downButton), countView::setText);
=======
        TextView result = (TextView) findViewById(R.id.tv_result);
        View view = new View(this);

        view.setOnClickListener(v -> foo());

        List<Integer> list = Arrays.asList(1, 2, 3);
        list.map(x -> x + 1)
            .forEach(System.out::println);

        Observable<Integer> stream = Observable.just(1, 2, 3);
        stream.map(x -> x + 1)
              .subscribe(System.out::println);

        View upButton = new View(this);
        View downButton = new View(this);

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

        view.setOnClickListener(__ -> incrementCount());


        Observable<Long> upTicks = Observable.interval(5, TimeUnit.SECONDS);
        Observable<?> downTicks = Observable.timer(10, TimeUnit.MINUTES);
        Counter.count(upTicks, downTicks)
                .compose(Counter::format)
                .map(String::toUpperCase)
                .map(Voice::textToSpeech)
                .subscribe(this::playSound);
    }

    private void playSound(Sound sound) {

>>>>>>> 3ff1ee7b0d1c79618d4396dfd3eecd7ac9d7d0c6
    }

    private static class Sound {

    }

    private static class Voice {
        static Sound textToSpeech(String text) {
            return null;
        }
    }

    TextView countView = new TextView(this);


    private void incrementCount() {
        count += 1;
        updateCountView();
    }

    private void updateCountView() {
        countView.setText(String.valueOf(count));
    }

    private static void foo() {

    }

}
