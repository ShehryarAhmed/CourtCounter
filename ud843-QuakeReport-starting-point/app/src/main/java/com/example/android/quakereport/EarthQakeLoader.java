package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by android on 10/9/2016.
 */
public class EarthQakeLoader extends AsyncTaskLoader<List<customclass>>

{
    private static final String LOG_TAG = EarthQakeLoader.class.getName();

    private String murl;
    public EarthQakeLoader(Context context,String url){
        super(context);
        murl = url;
    }
    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG,"OnStartLoader");
           forceLoad();

    }

    @Override
    public List<customclass> loadInBackground() {
        Log.e(LOG_TAG,"LoadInBackGround");

        if(murl == null){
            return null;
        }
        List<customclass> earthquakes = QueryUtils.fetchEarthquakeData(murl);
        return earthquakes;

    }
}
