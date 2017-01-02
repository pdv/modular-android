package me.pdv.modular;

/**
 * Created by pdv on 1/1/17.
 */

public class UserStateHolder {

    private TeamsProvider teamsProvider;

    public UserStateHolder(TeamsProvider teamsProvider) {
        this.teamsProvider = teamsProvider;
    }

    public TeamsProvider getTeamsProvider() {
        return teamsProvider;
    }

}
