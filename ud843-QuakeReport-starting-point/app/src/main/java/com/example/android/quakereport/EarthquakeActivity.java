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
import android.content.Intent;
import android.net.Uri;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<customclass>> {

    private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    public Loader<List<customclass>> onCreateLoader(int id, Bundle args) {
        return new EarthQakeLoader(this,QueryUtils.SAMPLE_JSON_RESPONSE);
    }

    @Override
    public void onLoadFinished(Loader<List<customclass>> loader, List<customclass> data) {
        madapter.clear();
        if(madapter != null && data.isEmpty()){
            madapter.addAll(data);
        }


    }

    @Override
    public void onLoaderReset(Loader<List<customclass>> loader) {
     madapter.clear();
    }

    private customadapter madapter;

    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);




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

        LoaderManager loaderManager =  getLoaderManager();

        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);


    }
    private class EarthQuakeAsync extends AsyncTask<String,Void, List<customclass>>{
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
        }
    }


