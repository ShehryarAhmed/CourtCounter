package com.example.android.habittracking;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.habittracking.DataBase.HabitContract;
import com.example.android.habittracking.DataBase.HabitDBhelper;

/**
 * Created by android on 12/4/2016.
 */
public class getHabitDetail extends AppCompatActivity {

    private EditText mhabitTitle;

    private Button mhabitAddButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.gethabitdetail);

        mhabitTitle = (EditText) findViewById(R.id.title);

        mhabitAddButton = (Button) findViewById(R.id.add_button);

        mhabitAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addHabit();
                finish();
            }
        });
    }

    private void addHabit() {

        String titleString = mhabitTitle.getText().toString().trim();

        HabitDBhelper mHabitDBhelper = new HabitDBhelper(this);

        SQLiteDatabase db = mHabitDBhelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HabitContract.HABIT_TITLE, titleString);

        db.insert(HabitContract.TABLE_NAME, null, values);

    }

}




