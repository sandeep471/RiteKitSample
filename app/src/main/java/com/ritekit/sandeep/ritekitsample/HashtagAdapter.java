package com.ritekit.sandeep.ritekitsample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sandeep on 7/31/2017.
 */

public class HashtagAdapter extends ArrayAdapter<HashtagList> {

    public HashtagAdapter(Context context, List<HashtagList> events) {
        super(context, 0, events);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hashtag_list, parent, false);
        }

        HashtagList hashtagList = getItem(position);

        TextView hashtag = (TextView) listItemView.findViewById(R.id.hashtag);
        TextView tweet = (TextView) listItemView.findViewById(R.id.tweet_count);
        TextView retweet = (TextView) listItemView.findViewById(R.id.retweet_count);

        String h = "#"+hashtagList.getHashtag();
        String t = String.valueOf(hashtagList.getTweet())+" tweets";
        String r = String.valueOf(hashtagList.getRetweet())+" retweets";

       // Log.i("In HashtagAdapter", h+" "+t+" "+r);

        hashtag.setText(h);
        tweet.setText(t);
        retweet.setText(r);

        return listItemView;
    }
}
