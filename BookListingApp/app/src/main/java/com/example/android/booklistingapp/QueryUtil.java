package com.example.android.booklistingapp;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 10/12/2016.
 */
public class QueryUtil {


    private static final String LOG_TAG = QueryUtil.class.getSimpleName();
    public static final String Request_url = "https://www.googleapis.com/books/v1/volumes?q=c&key=AIzaSyD1XEZaVzDVLpFOLL5vsGbHEQGH32klEhg";

    public static ArrayList<Detail> ExtractFeatureFromJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        ArrayList<Detail> list = new ArrayList<>();
        try{
            JSONObject basejsonobject = new JSONObject(json);
            JSONArray jsonArray = basejsonobject.getJSONArray("items");
            if(jsonArray.length()>0){
                for(int i = 0; i < jsonArray.length(); i++){
                    String author = "Author: ";
                    String title = "";
                    String category = "";
                    String publisher = "Publisher: ";
                    double ratingBar;
                    JSONObject itemjsonobject = jsonArray.getJSONObject(i);
                    JSONObject VoulmeInfo = itemjsonobject.getJSONObject("volumeInfo");
                     title = VoulmeInfo.getString("title");
                    StringBuilder authorstringbulider = new StringBuilder();
                    if(VoulmeInfo.has("authors")){
                        JSONArray jsonAuthorArray = VoulmeInfo.getJSONArray("authors");
                        for (int j = 0; j< jsonAuthorArray.length(); j++){
                            if(j>0){
                                authorstringbulider.append(", ");
                            }
                            author = authorstringbulider.append(jsonAuthorArray.getString(j)).toString();
                            }

                    }
                    if (VoulmeInfo.isNull("averageRating")) {
                        // Default unrated value? Hmm...
                        ratingBar = 5;
                    } else {
                        ratingBar = VoulmeInfo.getDouble("averageRating");
                    }
                    JSONArray categories = VoulmeInfo.getJSONArray("categories");

                    if (categories.length() > 0) {
                        for (int j = 0; j < categories.length(); j++) {
                            category += categories.optString(j) + " ";
                        }
                    }

                    JSONObject bookPictures = VoulmeInfo.getJSONObject("imageLinks");

                    String picture = bookPictures.getString("thumbnail");


                    list.add(new Detail(title,author,ratingBar,category,R.drawable.hrysanthemum));
                }
            }
         }
        catch (JSONException e){
            Log.e(LOG_TAG,"Problem during retrieve data from google Book API "+e);
        }
        return list;
    }

    /*private Bitmap downloadImage(String thumbnail){
        Bitmap bitmap = null;
        IOException in = null;

        try {
            URL url = createUrl(thumbnail);
            in = makeHttpRequest(url);
        }
        catch (IOException e){
            e.printStackTrace(e);
        }
    }*/

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public URL createUrl(String request_url){
        URL url;
        try{
            url = new URL(request_url);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG,"During Creation Url Error"+e);
            return null;
        }
        return url;
    }
    public String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                Log.e(LOG_TAG, "Response Code: " + urlConnection.getResponseCode());
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error Response Code: " + urlConnection.getResponseCode()
                        + " " + url.toString());
            }

        } catch (Exception e) {
            if (urlConnection.getResponseCode() != 200) {
                Log.e(LOG_TAG, "Error Retrieving Earthquake JSON results", e);
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }
}



