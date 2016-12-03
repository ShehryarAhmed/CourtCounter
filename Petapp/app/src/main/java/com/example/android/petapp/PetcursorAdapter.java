package com.example.android.petapp;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by android on 11/6/2016.
 */
public class PetcursorAdapter extends CursorAdapter {
    PetcursorAdapter(Context context, Cursor cursor){
        super(context,cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.listitems,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView Breed = (TextView) view.findViewById(R.id.breed);

        String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));

        int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));

        Name.setText(body);

        Breed.setText(String.valueOf(priority));

    }
}
