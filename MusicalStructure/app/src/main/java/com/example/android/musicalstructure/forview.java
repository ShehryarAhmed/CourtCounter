package com.example.android.musicalstructure;

/**
 * Created by android on 9/17/2016.
 */
public class forview {
    private int imageid;
    private String description;
    private int backgroundcolor;
    private int audioresourceid;

    public forview(final  int id, final String msg,int bgcolor,int audio){
        this.imageid = id;
        this.description = msg;
        this.backgroundcolor = bgcolor;
        this.audioresourceid = audio;
    }

    public int getImageid() {
        return imageid;
    }

    public String getDescription() {
        return description;
    }

    public int getBackgroundcolor() {
        return backgroundcolor;
    }

    public int getAudioresourceid() {
        return audioresourceid;
    }
}
