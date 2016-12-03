package com.example.android.sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.android.sqlite.Adapter.ProductDisplayAdapter;
import com.example.android.sqlite.Database.ProductContract;
import com.example.android.sqlite.Database.Productdphelper;
import com.example.android.sqlite.Model.ProductDetail;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Productdphelper mproductdphelper = new Productdphelper(this);

    ProductDisplayAdapter mproductDisplayAdapter;

    ArrayList<ProductDetail> mproduct_arrayList;


    @Override
    protected void onStart() {

        super.onStart();

        setAdapterInListview(displayDatabaseInfo());

    }

    private void setAdapterInListview(ArrayList<ProductDetail> displaydata) {

        ListView listView = (ListView) findViewById(R.id.list);

        mproductDisplayAdapter = new ProductDisplayAdapter(this, displaydata);

        listView.setAdapter(mproductDisplayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fb = (FloatingActionButton) findViewById(R.id.fab);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "adsada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GetProductDetail.class);
                startActivity(intent);
            }
        });
        ListView listView = (ListView) findViewById(R.id.list);

        mproductDisplayAdapter = new ProductDisplayAdapter(this, displayDatabaseInfo());

        listView.setAdapter(mproductDisplayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                int currentProductId = mproduct_arrayList.get(position).get_mid();

                mshow_Dialog(currentProductId, position);

            }
        });

    }

    private ArrayList<ProductDetail> displayDatabaseInfo() {

        // Create and/or open a database to read from it

        SQLiteDatabase db = mproductdphelper.getReadableDatabase();

        mproduct_arrayList = new ArrayList<>();

        // Define a projection that specifies which columns from the database

        // you will actually use after this query.

        String[] projection = {
                ProductContract.ProductEntry.mID,
                ProductContract.ProductEntry.PRODUCT_TITLE,
                ProductContract.ProductEntry.PRODUCT_QUANTITY,
                ProductContract.ProductEntry.PRODUCT_price,

        };

        // Perform a query on the product table

        Cursor cursor = db.query(
                ProductContract.ProductEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        try {

            // Figure out the index of each column

            int product_idColumIndex = cursor.getColumnIndex(ProductContract.ProductEntry.mID);

            int product_titleColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.PRODUCT_TITLE);

            int product_priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.PRODUCT_price);

            int product_quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.PRODUCT_QUANTITY);


            // Iterate through all the returned rows in the cursor

            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.

                int product_currentid = cursor.getInt(product_idColumIndex);

                String product_currenttitle = cursor.getString(product_titleColumnIndex);

                int product_currentquantity = cursor.getInt(product_quantityColumnIndex);

                int product_currentprice = cursor.getInt(product_priceColumnIndex);

                mproduct_arrayList.add(new ProductDetail(product_currentid, product_currenttitle, product_currentquantity, product_currentprice));

            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
        return mproduct_arrayList;

    }

    private void mshow_Dialog(final int productId, final int getitemposition) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                mproductdphelper.deleteProduct(productId);
                mproduct_arrayList.remove(getitemposition);
                mproductDisplayAdapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton(R.string.sale, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                {
                    sale_alert(productId, getitemposition);

                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void sale_alert(final int productId, final int getitemposition) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.sale_layout, null);

        final EditText meditText = (EditText) view.findViewById(R.id.edit_saleOrder);

        builder.setTitle("Sale");

        builder.setMessage("Write the Quantity to Sale ...?");

        builder.setPositiveButton("Sale", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final int id = productId;

                final int howManySale = Integer.valueOf(meditText.getText().toString().trim());

                final int mcurrentQuantity = mproduct_arrayList.get(getitemposition).getMproduct_quantity();

                final int noOfSale = mcurrentQuantity - howManySale;
                Log.e("check", "" + productId + " " + noOfSale + " " + howManySale + "currentQuantity" + mcurrentQuantity);

                ProductDetail productDetail = new ProductDetail((mproduct_arrayList.get(getitemposition).get_mid()),
                        mproduct_arrayList.get(getitemposition).getMproduct_title(), noOfSale, (mproduct_arrayList.get(getitemposition).getMproduct_price()));

                mproduct_arrayList.set(getitemposition, productDetail);

                mproductDisplayAdapter.notifyDataSetChanged();

                mproductdphelper.updateProduct(id, noOfSale);


            }
        });
        builder.setView(view);
        builder.create().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_mainvieww.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_mainview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.show_item_history:
                setAdapterInListview(displayDatabaseInfo());
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.show_sale_history:
                setAdapterInListview(displayDatabaseInfo());
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
/*
    private ArrayList<ProductDetail> displayitemhistory() {
    }*/

}
