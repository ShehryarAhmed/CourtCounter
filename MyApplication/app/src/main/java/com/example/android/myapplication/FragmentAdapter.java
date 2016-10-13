package com.example.android.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by android on 10/12/2016.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    FragmentAdapter(Context context, FragmentManager fm){
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ComputerScience();

            case 1:
                return new Programming();
            case 2:
                return new Health();
            case 3:
                return new Managment();
            default:
                return new AllBooks();
        }

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "ComputerScience";
            case 1:
                return "Programming";
            case 2:
                return "Health";
            case 3:
                return "Management";
            default:
                return "AllBooks";
        }
    }
}
