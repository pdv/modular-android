package me.pdv.modular;

import android.app.Application;

/**
 * Created by pdv on 1/1/17.
 */

public class ModularApplication extends Application {

    private UserStateHolder userStateHolder;

    @Override
    public void onCreate() {
        super.onCreate();

        this.userStateHolder = new UserStateHolder(new TeamsProvider());
    }

    public UserStateHolder getUserStateHolder() {
        return userStateHolder;
    }

}
