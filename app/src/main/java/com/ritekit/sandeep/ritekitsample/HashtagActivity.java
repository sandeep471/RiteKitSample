package com.ritekit.sandeep.ritekitsample;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HashtagActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<HashtagList>>{

    private static final int LOADER_ID = 1;
    public static final String LOG_TAG = HashtagActivity.class.getName();

    private TextView mEmptyStateTextView;
    private ProgressBar spinner;
    private HashtagAdapter hashtagAdapter;
    private String TRENDING_URL = "https://api.ritekit.com/v1/search/trending?green=1&latin=1";
    private String INFLUENCERS_URL = "https://api.ritekit.com/v1/influencers/hashtag/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_hashtag);

        setTitle(R.string.trending_hashtags);
        ListView hashtagListView = (ListView) findViewById(R.id.hashtag_list);
        spinner = (ProgressBar) findViewById(R.id.loading_spinner);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_text_view);

        hashtagListView.setEmptyView(mEmptyStateTextView);

        hashtagAdapter = new HashtagAdapter(this, new ArrayList<HashtagList>());
        hashtagListView.setAdapter(hashtagAdapter);

        hashtagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashtagList hashtagList = hashtagAdapter.getItem(position);
                String url = INFLUENCERS_URL+hashtagList.getHashtag()+"?client_id="+getString(R.string.client_id);
                Log.i(LOG_TAG, "url: "+url);

                Intent intent = new Intent(HashtagActivity.this, InfluencersActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            spinner.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<HashtagList>> onCreateLoader(int id, Bundle args) {
        return new HashtagLoader(this, TRENDING_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<HashtagList>> loader, List<HashtagList> data) {
        Log.v(LOG_TAG, "In onLoadFinished");

        spinner.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_trending_hashtags);
        hashtagAdapter.clear();

        if (data != null && !data.isEmpty()) {
            hashtagAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<HashtagList>> loader) {
        Log.v(LOG_TAG, "In onLoaderReset");
        hashtagAdapter.clear();
    }
}
