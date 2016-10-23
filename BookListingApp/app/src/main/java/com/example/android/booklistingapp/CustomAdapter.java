package com.example.android.booklistingapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by android on 10/12/2016.
 */
public class CustomAdapter extends ArrayAdapter<Detail>{
    public CustomAdapter(Activity activity, List<Detail> list) {
        super(activity, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootview = convertView;
        if(rootview == null){
            rootview = LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }
        Detail detail = getItem(position);

        ImageView imageView = (ImageView) rootview.findViewById(R.id.imageIcon);

        new DownloadImageTask(imageView)
                .execute(detail.getMpicture());

        /*String url = detail.getMpicture();

        Uri uri = Uri.parse(url);

        imageView.setImageURI(uri);
*/
//        imageView.setVisibility(View.VISIBLE);

        TextView titleview = (TextView) rootview.findViewById(R.id.title);

        titleview.setText(detail.getmTitle());

        TextView authorview = (TextView) rootview.findViewById(R.id.authore);

        authorview.setText(detail.getmAuthor());

        RatingBar rating = (RatingBar) rootview.findViewById(R.id.ratingbar);

        rating.setRating((float) detail.getmRattingBar());

        TextView category = (TextView) rootview.findViewById(R.id.category);

        category.setText(detail.getmCategory());






        return rootview;



    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
