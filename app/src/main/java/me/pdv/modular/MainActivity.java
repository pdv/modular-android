package me.pdv.modular;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

        Search.Repository searchRepo = repository::getTeamsForSearch;
        Search.QueryView queryView = () -> RxTextView.textChanges(textView);
        Search.ResultsView resultsView = adapter::setTeams;
        Search.displaySearchResults(searchRepo, queryView, resultsView, Timber::e);
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
