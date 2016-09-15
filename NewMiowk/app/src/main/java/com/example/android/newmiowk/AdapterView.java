package com.example.android.newmiowk;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 9/9/2016.
 */
public class AdapterView extends ArrayAdapter<ItemInListView> {
    private int backgroundcolor = 0;

    private int imagebg = NOIMAGEBGCOLOR;
    public static final int NOIMAGEBGCOLOR = -1;
    //check Activity and Context
    AdapterView(Activity context, ArrayList<ItemInListView> item, int bgcolor,int bgimagecolor){
        super(context,0, item);
        backgroundcolor = bgcolor;
        imagebg = bgimagecolor;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemview = convertView;
        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.iteminlistview,parent,false);

        }
        ItemInListView Word1 = getItem(position);
        TextView miowktextview = (TextView) listitemview.findViewById(R.id.miowkword);
        miowktextview.setText(Word1.getmMiowklangauge());

        TextView defaulttview = (TextView) listitemview.findViewById(R.id.defaultword);
        defaulttview.setText(Word1.getMdefaultlangauge());

        ImageView imageView = (ImageView) listitemview.findViewById(R.id.image_view);
        if(Word1.hasimage()){
            imageView.setImageResource(Word1.getImageid());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listitemview.findViewById(R.id.list_item);

        int currentColor = ContextCompat.getColor(getContext(),backgroundcolor);
        textContainer.setBackgroundColor(currentColor);

        if(imagebg != -1){
            View imageviewsetbg = listitemview.findViewById(R.id.image_view);
            int imagecurrentcolor = ContextCompat.getColor(getContext(),imagebg);
            imageviewsetbg.setBackgroundColor(imagecurrentcolor);
        }
        else{

        }
        return listitemview;
}}
