package com.ritekit.sandeep.ritekitsample;

/**
 * Created by sandeep on 7/31/2017.
 */

public class InfluencersList {
    private String name;
    private int followers;

    public InfluencersList(String name, int followers) {
        this.name = name;
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }
}
