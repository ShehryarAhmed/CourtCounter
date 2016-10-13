package com.example.android.booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Detail> list = new ArrayList<>();
     /*   list.add(new Detail("Java","Asher&Shehryar"));
        list.add(new Detail("P.O.M","Asher"));
        list.add(new Detail("C++","Shehryar"));*/
        if (isOnline(getApplication())){
            BookAsyncTask bookAsyncTask = new BookAsyncTask();
            bookAsyncTask.execute(QueryUtil.Request_url);
        }
        else {
            Toast.makeText(this,"No internet",Toast.LENGTH_SHORT).show();
        }

        ListView listView = (ListView) findViewById(R.id.list);

         customAdapter = new CustomAdapter(this,list);

        listView.setAdapter(customAdapter);



    }
    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    private class BookAsyncTask extends AsyncTask<String, Void, ArrayList<Detail>> {

        private final String LOG_TAG = BookAsyncTask.class.getSimpleName();
        QueryUtil queryUtil = new QueryUtil();


        @Override
        protected ArrayList<Detail> doInBackground(String... urls) {

            //example url = https://www.googleapis.com/books/v1/volumes?q=Gladwell
            URL url = queryUtil.createUrl(urls[0]);

            String jsonResponse = "";
            try {
                jsonResponse = queryUtil.makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }
            ArrayList<Detail> books = queryUtil.ExtractFeatureFromJson(jsonResponse);
            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Detail> books) {
            customAdapter.clear();

            if (books != null) {
                customAdapter.addAll(books);
            }
        }

    }}
