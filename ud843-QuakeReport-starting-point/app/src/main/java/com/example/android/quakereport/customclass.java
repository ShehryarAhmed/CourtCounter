package com.example.android.quakereport;

import java.sql.Time;
import java.util.Date;

/**
 * Created by android on 9/21/2016.
 */
public class customclass  {

    private double magnitude;

    private String place;

    private long time;

    private String Url;


    public customclass(final double magnitude, final String place, final long time,final String url){
        this.magnitude = magnitude;
        this.place = place;
        this.time = time;
        this.Url = url;

    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public String getUrl() {
        return Url;
    }





}
