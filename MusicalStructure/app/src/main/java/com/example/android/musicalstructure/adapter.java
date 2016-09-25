package com.example.android.musicalstructure;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 9/17/2016.
 */
public class adapter extends ArrayAdapter<forview> {
    private int backgroundcolor = 0;
    private int imagebg = NOIMAGEBGCOLOR;
    public static final int NOIMAGEBGCOLOR = -1;
    adapter(Activity context, ArrayList<forview> item, int bgcolor, int bgimagecolor){
        super(context,0, item);
        backgroundcolor = bgcolor;
        imagebg = bgimagecolor;

    }

    @Override
    public View getView(int position, View Convertview, ViewGroup parent) {
        View listitemview = Convertview;
        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.listitemview,null);

        }
        forview forviews = getItem(position);

        ImageView imageView = (ImageView) listitemview.findViewById(R.id.frontimage);
        imageView.setImageResource(forviews.getImageid());
        imageView.setVisibility(View.VISIBLE);

        TextView textView = (TextView) listitemview.findViewById(R.id.Description);
        textView.setText(forviews.getDescription());


        View textContainer = listitemview.findViewById(R.id.layouts);
        int currentColor = ContextCompat.getColor(getContext(),backgroundcolor);
        textContainer.setBackgroundColor(currentColor);

        if(imagebg != -1){
            View imageviewsetbg = listitemview.findViewById(R.id.frontimage);
            int imagecurrentcolor = ContextCompat.getColor(getContext(),imagebg);
            imageviewsetbg.setBackgroundColor(imagecurrentcolor);
        }


        return listitemview;
     }

}
