package com.example.android.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by android on 10/26/2016.
 */
public class BookLoader extends AsyncTaskLoader<List<Detail>> {
    private String mURl;
    public BookLoader(Context context, String url){
        super(context);
        mURl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Detail> loadInBackground() {
        List<Detail> BookLists = QueryUtil.ExtractFeatureFromJson(mURl);
        return BookLists;
    }
}
