package com.example.android.habittracking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.habittracking.Adapter.HabitAdapter;
import com.example.android.habittracking.DataBase.HabitContract;
import com.example.android.habittracking.DataBase.HabitDBhelper;
import com.example.android.habittracking.DataBase.HabitDetail;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    HabitDBhelper mhabitDBhelper = new HabitDBhelper(this);

    HabitAdapter mhabitAdapter ;

    ArrayList<HabitDetail> mhabitArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdapterInListview(displayDatabaseInfo());
    }


    @Override
    protected void onStart() {

        super.onStart();

        setAdapterInListview(displayDatabaseInfo());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainview,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.show_item_history:

                Intent intent = new Intent(MainActivity.this,getHabitDetail.class);

                startActivity(intent);

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.show_sale_history:

                mhabitDBhelper.deleteALLHabits(mhabitArraylist,mhabitAdapter);

                mhabitAdapter = new HabitAdapter(this, displayDatabaseInfo());



                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    private void setAdapterInListview(ArrayList<HabitDetail> displaydata) {

        ListView listView = (ListView) findViewById(R.id.list);

        mhabitAdapter = new HabitAdapter(this, displaydata);

        listView.setAdapter(mhabitAdapter);

    }


    private ArrayList<HabitDetail> displayDatabaseInfo() {

        // Create and/or open a database to read from it

        SQLiteDatabase db = mhabitDBhelper.getReadableDatabase();

        mhabitArraylist = new ArrayList<>();

        // Define a projection that specifies which columns from the database

        // you will actually use after this query.

        String[] projection = {
                HabitContract.mID,
                HabitContract.HABIT_TITLE
        };

        // Perform a query on the product table

        Cursor cursor = db.query(
                HabitContract.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        try {

            // Figure out the index of each column

            int habit_idColumIndex = cursor.getColumnIndex(HabitContract.mID);

            int habit_titleColumnIndex = cursor.getColumnIndex(HabitContract.HABIT_TITLE);

            // Iterate through all the returned rows in the cursor

            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.

                int habit_currentid = cursor.getInt(habit_idColumIndex);

                String habit_currenttitle = cursor.getString(habit_titleColumnIndex);

                mhabitArraylist.add(new HabitDetail(habit_currentid, habit_currenttitle));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
        return mhabitArraylist;

    }



}
