package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by android on 10/9/2016.
 */
public class EarthQakeLoader extends AsyncTaskLoader<List<customclass>>

{
    private String murl;
    public EarthQakeLoader(Context context,String url){
        super(context);
        murl = url;
    }
    @Override
    protected void onStartLoading() {
           forceLoad();

    }

    @Override
    public List<customclass> loadInBackground() {

        if(murl == null){
            return null;
        }
        List<customclass> earthquakes = QueryUtils.fetchEarthquakeData(murl);
        return earthquakes;

    }
}
