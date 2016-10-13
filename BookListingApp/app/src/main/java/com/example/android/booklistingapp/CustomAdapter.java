package com.example.android.booklistingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        TextView titleview = (TextView) rootview.findViewById(R.id.title);

        TextView authorview = (TextView) rootview.findViewById(R.id.authore);

        titleview.setText(detail.getTitle());

        authorview.setText(detail.getAuthor());

        return rootview;



    }
}
