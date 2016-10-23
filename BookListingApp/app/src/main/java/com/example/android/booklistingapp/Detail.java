package com.example.android.booklistingapp;

/**
 * Created by android on 10/12/2016.
 */
public class Detail {
    private String mTitle;
    private String mAuthor;
    private double mRattingBar;
    private String mCategory;
    private String mpicture;


    public Detail(String title, String author, double rattingBar,String category,String picture) {
        this.mTitle = title;
        this.mAuthor = author;
        this.mRattingBar = rattingBar;
        this.mCategory = category;
        this.mpicture = picture;

    }


    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public double getmRattingBar() {
        return mRattingBar;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getMpicture() {
        return mpicture;
    }

}
