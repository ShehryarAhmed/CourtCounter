package com.example.android.petapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.petapp.DataBase.PetContract;
import com.example.android.petapp.DataBase.PetDBhelper;

/**
 * Created by android on 10/30/2016.
 */
public class EditActivity extends AppCompatActivity{

    private EditText mpet_name ;

    private EditText mpet_breed ;

    private EditText mpet_weight ;

    private Spinner mpet_gender_Spinner ;

    private int mgender;

    private Pets pets =  new Pets();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editoractivity);

        mpet_name = (EditText) findViewById(R.id.edit_pet_name);
        mpet_breed = (EditText) findViewById(R.id.edit_pet_breed );
        mpet_weight = (EditText) findViewById(R.id.edit_pet_weight);
        mpet_gender_Spinner = (Spinner) findViewById(R.id.pet_gender);

        setupSpinner();

    }

    private void insertpet(){

        String nameString = mpet_name.getText().toString().trim();
       pets.setPet_name(nameString);
        String breedString = mpet_breed.getText().toString().trim();
        pets.setPet_breed(breedString);
        String weightString = mpet_weight.getText().toString().trim();
        int weight = Integer.parseInt(weightString);
        pets.setPet_Weight(weight);


        PetDBhelper mpetDBhelper = new PetDBhelper(this);

        SQLiteDatabase db =  mpetDBhelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PetContract.PetEntry.COLUMN_PET_NAME,nameString);

        values.put(PetContract.PetEntry.COLUMN_PET_BREED,breedString);

        values.put(PetContract.PetEntry.COLUMN_PET_GENDER,mgender);

        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT,weight);



        long newRowId = db.insert(PetContract.PetEntry.TABLE_NAME, null, values);

        if(newRowId != -1){
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Error for saving pet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupSpinner(){
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_dropdown_item_1line);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mpet_gender_Spinner.setAdapter(genderSpinnerAdapter);

        mpet_gender_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selction = (String) adapterView.getItemAtPosition(position);
                Log.e("selection"," Selection :"+selction);
              /*  if(!TextUtils.isEmpty(selction)){*/

                Log.e("selection"," Selection :"+R.string.gender_male);

                    if(selction.equals(getString(R.string.gender_male))){


                        mgender = PetContract.PetEntry.GENDER_MALE;
                    }
                    else if(selction.equals(getString(R.string.gender_female))){
                        mgender = PetContract.PetEntry.GENDER_FEMALE;
                    }
                    else {
                        mgender = PetContract.PetEntry.GENDER_UNKNOWN;
                    }
                    pets.setPetGender(mgender);
             /*   }
             */
                Log.e("selection"," Selection :"+mgender);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mgender = PetContract.PetEntry.GENDER_UNKNOWN;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menueditor,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_save:
                //Toast.makeText(this, "action save", Toast.LENGTH_SHORT).show();
                insertpet();
                //Exit Activity
                finish();
                return true;

            case R.id.delete_all_data:
                Toast.makeText(this, "delte data", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
