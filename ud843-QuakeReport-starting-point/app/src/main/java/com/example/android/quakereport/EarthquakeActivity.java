/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<customclass>> {


    private TextView mEmptyStateTextView;

    private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    public Loader<List<customclass>> onCreateLoader(int id, Bundle args) {
        Log.e(LOG_TAG,"OncreateLoader");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        String minMagnitude = sharedPreferences.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));
        String orderBy = sharedPreferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );


        Uri baseUri = Uri.parse(QueryUtils.SAMPLE_JSON_RESPONSE);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        return new EarthQakeLoader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<customclass>> loader, List<customclass> data) {
        Log.e(LOG_TAG,"OnLoaderFinished");
        mEmptyStateTextView.setText("NO EARTHQUAKE");
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        madapter.clear();
        if(data != null && !data.isEmpty()){
            madapter.addAll(data);
        }


    }

    @Override
    public void onLoaderReset(Loader<List<customclass>> loader) {
        Log.e(LOG_TAG,"OnLoaderReset");
        madapter.clear();
    }

    private customadapter madapter;

    private static final String LOG_TAG = EarthquakeActivity.class.getName();



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent SettingsIntent =new Intent(this,SettingActivity.class);
            startActivity(SettingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(LOG_TAG,"Oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);





        // Create a fake list of earthquake locations.


        /*earthquakes.add(new customclass(0.12,"San francisco","sep 19,2016"));
        earthquakes.add(new customclass(0.1,"San francisco","sep 19,2016"));
        earthquakes.add(new customclass(0.1,"San francisco","sep 19,2016"));
        earthquakes.add(new customclass(0.1,"San francisco","sep 19,2016"));
        earthquakes.add(new customclass(0.1,"San francisco","sep 19,2016"));
        earthquakes.add(new customclass(0.1,"San francisco","sep 19,2016"));*/

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes

        madapter = new customadapter(this, new ArrayList<customclass>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setEmptyView(mEmptyStateTextView);
        earthquakeListView.setAdapter(madapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customclass currentearthqhack = madapter.getItem(position);
                Uri earthquackuri = Uri.parse(currentearthqhack.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW,earthquackuri);
                startActivity(i);
            }
        });

/*
        EarthQuakeAsync task = new EarthQuakeAsync();
        task.execute(QueryUtils.SAMPLE_JSON_RESPONSE);
*/
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager =  getLoaderManager();
            Log.e(LOG_TAG,"OnInitLoader");
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

        }
else {
         View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText("no_internet_connection");
        }


    }
    /*private class EarthQuakeAsync extends AsyncTask<String,Void, List<customclass>>{
        @Override
        protected List<customclass> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<customclass> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<customclass> data) {
            // Clear the adapter of previous earthquake data
            madapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                 madapter.addAll(data);
            }
        }
        }*/
    }


