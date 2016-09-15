package com.example.android.newmiowk;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by android on 9/9/2016.
 */
public class Numbers extends AppCompatActivity  {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_category);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new NumbersFragment()).commit();
    }
}