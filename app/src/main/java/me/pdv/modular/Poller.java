package me.pdv.modular;

import rx.Observable;
import rx.Subscription;

/**
 * Created by pdv on 1/28/17.
 */

public class Poller {

    static class Update {
        static Update fromPush(PushReadData pushReadData) {
            return new Update();
        }
    }
    static class PushReadData {
    }

    interface GameId extends M.Source<String> {}
    interface StreamId extends M.Source<String> {}
    interface Marker extends M.Source<String> {}
    interface Network {
        PushReadData getPushData(String gameId, String streamId, String marker);
    }
    interface Updatable extends M.Sink<Update> {}


    public static Subscription poll(GameId gameId,
                                    StreamId streamId,
                                    Marker marker,
                                    Network network,
                                    Updatable updatable) {
        return Observable.zip(gameId.get(), streamId.get(), marker.get(), network::getPushData)
                .map(Update::fromPush)
                .subscribe(updatable::put);
    }

}
