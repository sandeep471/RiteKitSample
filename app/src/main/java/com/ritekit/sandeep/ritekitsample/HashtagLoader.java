package com.ritekit.sandeep.ritekitsample;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by sandeep on 7/31/2017.
 */

public class HashtagLoader extends AsyncTaskLoader<List<HashtagList>> {

    private String mUrl;
    public HashtagLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v("HashtagLoader", "In onStartLoading");
    }

    @Override
    public List<HashtagList> loadInBackground() {
        Log.v("HashtagLoader", "In loadInBackground");
        // Perform the network request, parse the response, and extract a list of events.
        List<HashtagList> events = Utils.fetchTrendingHashtagList(mUrl);
        return events;
    }
}
