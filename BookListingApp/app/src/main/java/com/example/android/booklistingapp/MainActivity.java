package com.example.android.booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String completeUrl;
    CustomAdapter customAdapter;
    ArrayList<Detail> tempBooklist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText editText = (EditText) findViewById(R.id.serach_query);



        Button button = (Button) findViewById(R.id.serach_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTextFromEdittext = editText.getText().toString();

                completeUrl = QueryUtil.Request_url.toString();
                completeUrl += getTextFromEdittext + QueryUtil.API_KEY;
                editText.setText("");

                BookAsyncTask bookAsyncTask = new BookAsyncTask();

                bookAsyncTask.execute();

            }
        });
        ArrayList<Detail> list = new ArrayList<>();
   /*     list.add(new Detail("Java","Asher&Shehryar"));
        list.add(new Detail("P.O.M","Asher"));
        list.add(new Detail("C++","Shehryar"));*/

        /*if (isOnline(getApplication())){

        }
        else {
            Toast.makeText(this,"No internet",Toast.LENGTH_SHORT).show();
        }*/

        ListView listView = (ListView) findViewById(R.id.list);
            customAdapter = new CustomAdapter(this, list);

            listView.setAdapter(customAdapter);



    }
    public boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    private class BookAsyncTask extends AsyncTask<URL, Void, ArrayList<Detail>> {

        private final String LOG_TAG = BookAsyncTask.class.getSimpleName();
        QueryUtil queryUtil = new QueryUtil();

        @Override
        protected ArrayList<Detail> doInBackground(URL... urls) {
            URL url = null ;


            url = queryUtil.createUrl(completeUrl);

            String jsonResponse = "";
            try{
                jsonResponse = queryUtil.makeHttpRequest(url);
            }catch (IOException e){
                Log.e("","");
            }

            ArrayList<Detail> books = queryUtil.ExtractFeatureFromJson(jsonResponse);

            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Detail> books) {


            if (books == null) {
                return;
            }
            updateUi(books);
        }

    }

    private void updateUi(ArrayList<Detail> books) {

        tempBooklist = books;

        if (books != null) {
            ListView bookListView = (ListView) findViewById(R.id.list);

            CustomAdapter adapter = new CustomAdapter(this, books);

            bookListView.setAdapter(adapter);
        } else {
            Log.e("UPDAte", "Still suffering from random void errors and no results with a correct string");
        }
    }}
