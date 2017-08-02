package com.ritekit.sandeep.ritekitsample;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by sandeep on 7/31/2017.
 */

public class InfluencersLoader extends AsyncTaskLoader<List<InfluencersList>> {

    private String mUrl;
    public InfluencersLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v("InfluencersLoader", "In onStartLoading");
    }

    @Override
    public List<InfluencersList> loadInBackground() {
        Log.v("InfluencersLoader", "In loadInBackground");
        // Perform the network request, parse the response, and extract a list of events.
        List<InfluencersList> events = Utils.fetchInfluencersList(mUrl);
        return events;
    }
}