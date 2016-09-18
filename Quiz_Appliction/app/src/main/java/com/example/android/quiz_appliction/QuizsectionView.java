package com.example.android.quiz_appliction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 9/19/2016.
 */
public class QuizsectionView extends AppCompatActivity {

    private TextView textView;

    private RadioGroup radioGroup;

    private RadioButton radioButton;

    public QuizsectionView(){

    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizsection);
        quiz();
    }
    //ArrayList<AllQuestionsAnswer> arrayList,AllQuestionsAnswer allQuestionsAnswer
    public void quiz(){

        textView =(TextView)findViewById(R.id.questionview);

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);

        radioButton = new RadioButton(this);

        radioButton.setText("hello1");

        RadioButton r = new RadioButton(this);

        r.setText("hello2");

        RadioButton r2 = new RadioButton(this);

        r2.setText("hello3");

        radioGroup.addView(radioButton);
        radioGroup.addView(r);
        radioGroup.addView(r2);

    }

}
