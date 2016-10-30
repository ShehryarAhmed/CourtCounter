package com.example.android.petapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.petapp.DataBase.PetContract;
import com.example.android.petapp.DataBase.PetDBhelper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Catalog extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private PetDBhelper mDbhelper = new PetDBhelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fb = (FloatingActionButton) findViewById(R.id.fab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Catalog.this, "fab", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Catalog.this, EditActivity.class);
                startActivity(intent);
            }
        });


        ListView listView = (ListView) findViewById(R.id.list);

        PetAdapter petAdapter = new PetAdapter(this, displayDatabaseInfo());

        listView.setAdapter(petAdapter);

    }

        private ArrayList<Pets> displayDatabaseInfo () {
            // Create and/or open a database to read from it
            SQLiteDatabase db = mDbhelper.getReadableDatabase();

            ArrayList<Pets> arrayList = new ArrayList<>();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    PetContract.PetEntry._ID,
                    PetContract.PetEntry.COLUMN_PET_NAME,
                    PetContract.PetEntry.COLUMN_PET_BREED,
                    PetContract.PetEntry.COLUMN_PET_GENDER,
                    PetContract.PetEntry.COLUMN_PET_WEIGHT};

            // Perform a query on the pets table
            Cursor cursor = db.query(
                    PetContract.PetEntry.TABLE_NAME,   // The table to query
                    projection,            // The columns to return
                    null,                  // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);                   // The sort order

           // TextView displayView = (TextView) findViewById(R.id.text_view_pet);

            try {
                // Create a header in the Text View that looks like this:
                //
                // The pets table contains <number of rows in Cursor> pets.
                // _id - name - breed - gender - weight
                //
                // In the while loop below, iterate through the rows of the cursor and display
                // the information from each column in this order.
                /*
                displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
                displayView.append(PetContract.PetEntry._ID + " - " +
                        PetContract.PetEntry.COLUMN_PET_NAME + " - " +
                        PetContract.PetEntry.COLUMN_PET_BREED + " - " +
                        PetContract.PetEntry.COLUMN_PET_GENDER + " - " +
                        PetContract.PetEntry.COLUMN_PET_WEIGHT + "\n");
*/
                // Figure out the index of each column
                int idColumnIndex = cursor.getColumnIndex(PetContract.PetEntry._ID);
                int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
                int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
                int genderColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
                int weightColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);

                // Iterate through all the returned rows in the cursor
                while (cursor.moveToNext()) {
                    // Use that index to extract the String or Int value of the word
                    // at the current row the cursor is on.
                    int currentID = cursor.getInt(idColumnIndex);
                    String currentName = cursor.getString(nameColumnIndex);
                    String currentBreed = cursor.getString(breedColumnIndex);
                    int currentGender = cursor.getInt(genderColumnIndex);
                    int currentWeight = cursor.getInt(weightColumnIndex);
                    // Display the values from each column of the current row in the cursor in the TextView
              /*      displayView.append(("\n" + currentID + " - " +
                            currentName + " - " +
                            currentBreed + " - " +
                            currentGender + " - " +
                            currentWeight));*/


                    arrayList.add(new Pets(currentName,currentBreed,currentGender,currentWeight));

                }

            } finally {
                // Always close the cursor when you're done reading from it. This releases all its
                // resources and makes it invalid.
                cursor.close();
            }
        return arrayList;
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucatalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummydata:
                Toast.makeText(this, "insert data", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.delete_all_data:
                Toast.makeText(this, "Delete data", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }
}
