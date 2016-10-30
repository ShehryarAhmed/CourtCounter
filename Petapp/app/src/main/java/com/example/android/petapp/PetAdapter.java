package com.example.android.petapp;

import android.app.Activity;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.petapp.DataBase.PetDBhelper;

import java.util.ArrayList;

/**
 * Created by android on 10/30/2016.
 */
public class PetAdapter extends ArrayAdapter<Pets> {
    public PetAdapter(Activity activity, ArrayList<Pets> petsArrayList){
        super(activity,0,petsArrayList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.listitems,parent,false);
        }
        Pets pets = getItem(position);

        TextView pet_name = (TextView) view.findViewById(R.id.name);

        pet_name.setText(pets.getPet_name());

        TextView pet_breed = (TextView) view.findViewById(R.id.breed);

        pet_breed.setText(pets.getPet_breed());

        TextView pet_gender = (TextView) view.findViewById(R.id.gender);

        pet_gender.setText(pets.getGender());

        TextView pet_wieght = (TextView) view.findViewById(R.id.wieght);

        pet_wieght.setText(""+pet_wieght);

        return view;
    }
}
