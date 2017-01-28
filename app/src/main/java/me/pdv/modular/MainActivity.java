package me.pdv.modular;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository();
        TeamsAdapter adapter = new TeamsAdapter();

        TextView textView = new TextView(this);

        RxTextView.textChanges(textView)
                .map(CharSequence::toString)
                .filter(string -> string.length() > 3)
                .flatMap(repository::getTeamsForSearch)
                .doOnError(Log::e)
                .subscribe(adapter::setTeams);

        RxAdapterView.itemClickEvents()
                .map(team -> new Intent(this, TeamDetailActivity.class).putExtra("teamId", team.division))
                .subscribe(this::startActivity);

        toolbar.onBackButtonPressed(this::finish);
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
