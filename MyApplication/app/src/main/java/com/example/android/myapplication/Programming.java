package com.example.android.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by android on 10/12/2016.
 */
public class Programming extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.displayinviewpager,container,false);
        TextView textView = (TextView) rootview.findViewById(R.id.text);
        textView.setText(""+"Programming");
        return rootview;
    }
}
