package com.example.android.booklistingapp;

/**
 * Created by android on 10/12/2016.
 */
public class Detail {
    private String Title;
    private String Author;

    public Detail(String title, String author) {
        this.Title = title;
        this.Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }
}
