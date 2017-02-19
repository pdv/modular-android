package me.pdv.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView countView = (TextView) findViewById(R.id.tv_count);
        Button upButton = (Button) findViewById(R.id.btn_up);
        Button downButton = (Button) findViewById(R.id.btn_down);
        Counter.count(RxView.clicks(upButton), RxView.clicks(downButton), countView::setText);
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
