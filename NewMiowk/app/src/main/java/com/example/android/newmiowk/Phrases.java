package com.example.android.newmiowk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by android on 9/9/2016.
 */
public class Phrases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_category);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new PhrasesFragment()).commit();
    }
}
