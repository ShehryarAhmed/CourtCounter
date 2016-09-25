package com.example.sayla.report_card;

/**
 * Created by sayla on 25/09/2016.
 */
public class coursedetail {

    private int Islamiat;
    private int Math;
    private int Urdu;
    private int English;
    private int Science;

    public coursedetail(final int islamiat,final int math,
                        final int urdu,final int english,
                        final int science){
        this.Islamiat = islamiat;
        this.Math = math;
        this.Urdu = urdu;
        this.English = english;
        this.Science = science;
    }

    public int getIslamiat() {
        return Islamiat;
    }

    public int getMath() {
        return Math;
    }

    public int getUrdu() {
        return Urdu;
    }

    public int getEnglish() {
        return English;
    }

    public int getScience() {
        return Science;
    }
}
