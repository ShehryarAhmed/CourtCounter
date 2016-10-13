package com.example.android.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner =(Spinner) findViewById(R.id.spinner);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        FragmentAdapter fragmentManager = new FragmentAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(fragmentManager);





    }
}
