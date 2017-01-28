package me.pdv.modular;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func0;
import rx.functions.Func1;

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
        Search.displaySearchResults(searchRepo, queryView, resultsView);
    }

    interface Map<K, V> {
        V get(K query);
    }
    interface Source<T> {
        Observable<T> get();
    }
    interface Sink<T> {
        void put(T t);
    }

    static final class Search {

        interface Repository extends Map<String, Observable<List<Team>>> {}
        interface QueryView extends Source<CharSequence> {}
        interface ResultsView extends Sink<List<Team>> {}

        public static Subscription displaySearchResults(Repository repository, QueryView queryView, ResultsView resultsView) {
            return queryView.get()
                    .map(CharSequence::toString)
                    .filter(string -> string.length() > 3)
                    .flatMap(repository::get)
                    .doOnError(Log::e)
                    .subscribe(resultsView::put);
        }
    }

    private Subscription showTeamDetailOnClick() {
        return RxAdapterView.itemClickEvents()
                .map(team -> new Intent(this, MainActivity.class).putExtra("teamId", team.division))
                .subscribe(this::startActivity);
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
