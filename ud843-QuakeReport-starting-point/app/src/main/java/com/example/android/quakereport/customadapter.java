package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;


/**
 * Created by android on 9/21/2016.
 */
public class customadapter extends ArrayAdapter<customclass>  {

    private static final String LOCATION_SEPARATER = " of ";

    private String primaryLocation;

    private String locationOffset;

    public customadapter(Activity activity, ArrayList<customclass> arrayList){
        super(activity,0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemview = convertView;

        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        customclass currentlist = getItem(position);

        TextView magnitude = (TextView) listitemview.findViewById(R.id.magnitudeview);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        int magnitudeColor = getMagnitudeColor(currentlist.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);

        magnitude.setText(formattedmag(currentlist.getMagnitude()));

        String OrignalLocation = currentlist.getPlace();

        split(OrignalLocation,LOCATION_SEPARATER);


        TextView near = (TextView) listitemview.findViewById(R.id.near);

        near.setText(locationOffset);

        TextView place = (TextView) listitemview.findViewById(R.id.place);

        place.setText(primaryLocation);

        TextView date = (TextView) listitemview.findViewById(R.id.date);

        Date dateobject = new Date(currentlist.getTime());

        String formatteddate = formatDate(dateobject);

        date.setText(formatteddate);

        TextView time = (TextView) listitemview.findViewById(R.id.time);

        String timeformate = formatTime(dateobject);

        time.setText(timeformate);

        return listitemview;

    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private void split(String Orignallocation, final String separater){
        if(Orignallocation.contains(separater)) {
            String nowsplit[] = Orignallocation.split(separater);
            locationOffset = nowsplit[0] += separater;
            primaryLocation = nowsplit[1];
        }
        else {
            locationOffset = getContext().getString(R.string.Near_the);
            primaryLocation = Orignallocation;
        }
    }
    private String formattedmag(double mag){
        DecimalFormat format = new DecimalFormat("0.0");
        return  format.format(mag);
    }
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}

