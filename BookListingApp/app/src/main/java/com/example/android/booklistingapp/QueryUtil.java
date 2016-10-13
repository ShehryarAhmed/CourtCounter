package com.example.android.booklistingapp;

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
    private final String Request_url = "https://www.googleapis.com/books/v1/volumes?q=";

    public static List<Detail> ExtractFeatureFromJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        ArrayList<Detail> list = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if(jsonArray.length()>0){
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject itemjsonobject = jsonArray.getJSONObject(i);
                    JSONObject VoulmeInfo = itemjsonobject.getJSONObject("volumeinfo");
                    String title = VoulmeInfo.getString("title");
                    StringBuilder author = new StringBuilder();
                    if(VoulmeInfo.has("authors")){
                        JSONArray jsonAuthorArray = VoulmeInfo.getJSONArray("authors");
                        for (int j = 0; j< jsonAuthorArray.length(); j++){
                            if(j>0){
                                author.append(", ");
                            }
                            author.append(jsonAuthorArray.getString(j));
                            }
                        list.add(new Detail(title,author.toString()));
                        return list;
                    }
                }
            }
         }
        catch (JSONException e){
            Log.e(LOG_TAG,"Problem during retrieve data from google Book API "+e);
        }
        return list;
    }


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

    public String makeHttpRequest(URL url) throws IOException{
        String JSONRespone = "";
        if (url == null){
            return JSONRespone;
        }
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                inputStream = httpURLConnection.getInputStream();
                JSONRespone = readFromStream(inputStream);
            }
            else {
                Log.e(LOG_TAG,"Error Respond code "+httpURLConnection.getResponseCode());
            }
            }
        catch (IOException e){
            Log.e(LOG_TAG,"During Retrieving Problem"+e);
                    }
        finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }

        }
        return JSONRespone;

            }

}
