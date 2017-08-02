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

public class InfluencersAdapter extends ArrayAdapter<InfluencersList> {

public InfluencersAdapter(Context context, List<InfluencersList> events) {
        super(context, 0, events);
        }

public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hashtag_list, parent, false);
        }

        InfluencersList influencersList = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.hashtag);
        TextView followers = (TextView) listItemView.findViewById(R.id.tweet_count);

        String n = influencersList.getName();
        String f = String.valueOf(influencersList.getFollowers())+" followers";

       // Log.i("In InfluencersAdapter", n+" "+f);

        name.setText(n);
        followers.setText(f);

        return listItemView;
        }
}

