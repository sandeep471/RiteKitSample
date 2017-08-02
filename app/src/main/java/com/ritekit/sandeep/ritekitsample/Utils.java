package com.ritekit.sandeep.ritekitsample;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep on 7/31/2017.
 */

class Utils {

    public static final String LOG_TAG = Utils.class.getName();

    public static List<HashtagList> fetchTrendingHashtagList(String mUrl) {

        ArrayList<HashtagList> trend_hashtag = new ArrayList<>();
        JSONObject hashtag_obj;
        String hashtag;
        int tweet;
        int retweet;

        try {
            hashtag_obj = new JSONObject(readUrl(mUrl));
            JSONArray hashtag_data = hashtag_obj.getJSONArray("tags");
           // Log.i(LOG_TAG, hashtag_data.toString());
            int length = hashtag_data .length();
            for(int i=0; i<length; i++) {
                JSONObject jObj = hashtag_data.getJSONObject(i);

                hashtag = jObj.getString("tag").toString();
                tweet = Integer.parseInt(jObj.getString("tweets"));
                retweet = Integer.parseInt(jObj.getString("retweets"));

                //Log.i(LOG_TAG, hashtag+" "+tweet+" "+retweet);

                HashtagList hashtagList = new HashtagList(hashtag, tweet, retweet);
                trend_hashtag.add(hashtagList);
            }

            } catch (Exception e) {
            e.printStackTrace();
        }
        return trend_hashtag;
    }

    public static List<InfluencersList> fetchInfluencersList(String mUrl) {

        ArrayList<InfluencersList> influencers = new ArrayList<>();
        JSONObject influencers_obj;
        String name;
        int followers;

        try {
            influencers_obj = new JSONObject(readUrl(mUrl));
            JSONArray influencers_data = influencers_obj.getJSONArray("influencers");
            //Log.i(LOG_TAG, influencers_data.toString());
            int length = influencers_data .length();
            for(int i=0; i<length; i++) {
                JSONObject jObj = influencers_data.getJSONObject(i);

                name = jObj.getString("username").toString();
                followers = Integer.parseInt(jObj.getString("followers"));

                //Log.i(LOG_TAG, name+" "+followers);

                InfluencersList influencersList = new InfluencersList(name, followers);
                influencers.add(influencersList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return influencers;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
