package com.example.android.habittracking.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.habittracking.DataBase.HabitDetail;
import com.example.android.habittracking.R;

import java.util.ArrayList;

/**
 * Created by android on 12/3/2016.
 */
public class HabitAdapter extends ArrayAdapter<HabitDetail> {

    public HabitAdapter(Activity activity, ArrayList<HabitDetail> productArrayList){

        super(activity,0,productArrayList);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);
        }

        HabitDetail mhabitDetail= getItem(position);

        TextView mtitleView = (TextView) view.findViewById(R.id.titleview);

        mtitleView.setText(""+ mhabitDetail.getmHabitTitle());

        return view;
    }
}
