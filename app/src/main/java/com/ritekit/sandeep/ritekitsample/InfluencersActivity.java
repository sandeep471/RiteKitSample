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

public class InfluencersActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<InfluencersList>>{

    private static final int LOADER_ID = 1;
    public static final String LOG_TAG = InfluencersActivity.class.getName();

    private TextView mEmptyStateTextView;
    private ProgressBar spinner;
    private InfluencersAdapter influencersAdapter;
    private String INFLUENCERS_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_influencers);

        setTitle(R.string.influencers);

        Intent intent = getIntent();
        INFLUENCERS_URL = intent.getStringExtra("url");
        Log.i(LOG_TAG, "intentUrl: "+intent.getStringExtra("url"));

        ListView influencerListView = (ListView) findViewById(R.id.influencers_list);
        spinner = (ProgressBar) findViewById(R.id.loading_spinner1);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_influencer_view);

        influencerListView.setEmptyView(mEmptyStateTextView);

        influencersAdapter = new InfluencersAdapter(this, new ArrayList<InfluencersList>());
        influencerListView.setAdapter(influencersAdapter);

        /*influencerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfluencersList influencersList = influencersAdapter.getItem(position);
                String url = INFLUENCERS_URL+hashtagList.getHashtag()+"?client_id="+getString(R.string.client_id);
                Log.i(LOG_TAG, "url: "+url);

                Intent intent = new Intent(HashtagActivity.this, InfluencersActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });*/

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
    public Loader<List<InfluencersList>> onCreateLoader(int id, Bundle args) {
        return new InfluencersLoader(this, INFLUENCERS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<InfluencersList>> loader, List<InfluencersList> data) {
        Log.v(LOG_TAG, "In onLoadFinished");

        spinner.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_influencers);
        influencersAdapter.clear();

        if (data != null && !data.isEmpty()) {
            influencersAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<InfluencersList>> loader) {
        Log.v(LOG_TAG, "In onLoaderReset");
        influencersAdapter.clear();
    }
}
