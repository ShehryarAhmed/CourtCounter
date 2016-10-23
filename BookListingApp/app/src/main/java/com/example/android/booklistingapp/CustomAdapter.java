package com.example.android.booklistingapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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

        ImageView imageView = (ImageView) rootview.findViewById(R.id.image);

        imageView.setImageResource(detail.getMpicture());

        imageView.setVisibility(View.VISIBLE);

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
}
