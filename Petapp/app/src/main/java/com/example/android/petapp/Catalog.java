package com.example.android.petapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Catalog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fb = (FloatingActionButton) findViewById(R.id.fab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Catalog.this,"fab",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Catalog.this,EditActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Pets> arrayList = new ArrayList<>();
        arrayList.add(new Pets());
        ListView listView = (ListView) findViewById(R.id.list);

        PetAdapter petAdapter = new PetAdapter(this,arrayList);

        listView.setAdapter(petAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucatalog,menu);
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
}
