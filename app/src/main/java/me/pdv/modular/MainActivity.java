package me.pdv.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.Locale;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView result = (TextView) findViewById(R.id.tv_result);

        Observable<Void> upClicks = RxView.clicks(findViewById(R.id.btn_up));
        Observable<Void> downClicks = RxView.clicks(findViewById(R.id.btn_down));
        Counter.count(upClicks, downClicks)
                .compose(Counter::format)
                .subscribe(result::setText);
    }


    static class MathUtil {
        public static int sum(Integer... ints) {
            int ret = 0;
            for (int i : ints) {
                ret += i;
            }
            return ret;
        }
    }

    static class Counter {

        public static Observable<Integer> count(Observable<Void> upClicks, Observable<Void> downClicks) {
            return Observable.merge(
                    upClicks.map(__ -> 1),
                    downClicks.map(__ -> -1))
                    .startWith(0)
                    .scan(0, MathUtil::sum);
        }

        public static Observable<String> format(Observable<Integer> count) {
            return count.map(countVal -> String.format(Locale.getDefault(), "%d", countVal));
        }

    }

    static class Timber {
        static void e(Throwable throwable) {

        }
    }

    public class TeamsAdapter {
        public void setTeams(List<Team> teams) {

        }
        public Observable<Team> clicks() {
            // TDOO
            return Observable.just(null);
        }
    }

    private Observable<Team> getTeams() {
        return Observable.just(null, null, null);
    }
}
