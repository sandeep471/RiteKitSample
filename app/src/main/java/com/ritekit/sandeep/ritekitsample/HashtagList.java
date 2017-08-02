package com.ritekit.sandeep.ritekitsample;

/**
 * Created by sandeep on 7/31/2017.
 */

public class HashtagList {
    private String hashtag;
    private int tweet;
    private int retweet;

    public HashtagList(String hashtag, int tweet, int retweet) {
        this.hashtag = hashtag;
        this.tweet = tweet;
        this.retweet = retweet;
    }

    public String getHashtag() {
        return hashtag;
    }

    public int getTweet() {
        return tweet;
    }

    public int getRetweet() {
        return retweet;
    }
}
