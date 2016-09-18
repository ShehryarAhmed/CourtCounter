package com.example.android.quiz_appliction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;

    private RelativeLayout quizsection;

    private RadioGroup radioGroups;

    private RadioButton radioButton;

    private Button startbutton;

    private Button nextbutton;

    private Button submit;

    private int marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startquiz();
    }

    private void startquiz(){

        startbutton = (Button) findViewById(R.id.start);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,QuizsectionView.class);
                startbutton.setVisibility(View.INVISIBLE);
                startActivity(i);

            }
        });
    }

}
