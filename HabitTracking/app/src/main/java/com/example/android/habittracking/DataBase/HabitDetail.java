package com.example.android.habittracking.DataBase;

/**
 * Created by android on 12/4/2016.
 */
public class HabitDetail {

    private int _mid;

    private String mHabitTitle;

    public HabitDetail(int _mid, String mHabitTitle) {
        this._mid = _mid;
        this.mHabitTitle = mHabitTitle;
    }

    public String getmHabitTitle() {
        return mHabitTitle;
    }
}
