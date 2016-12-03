package com.example.android.sqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by android on 11/22/2016.
 */
public class Productdphelper extends SQLiteOpenHelper {

    private static final String DATA_BASE = "Product.dp";

    private static final int DATABASE_VERSION = 1;

    public Productdphelper(Context context){
        super(context,DATA_BASE,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String  CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS "+ProductContract.ProductEntry.TABLE_NAME + " ("
                + ProductContract.ProductEntry.mID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ProductContract.ProductEntry.PRODUCT_TITLE + " TEXT NOT NULL, "
                + ProductContract.ProductEntry.PRODUCT_QUANTITY + " INTEGER NOT NULL"
                + ProductContract.ProductEntry.PRODUCT_price + " INTEGER NOT NULL);";
        Log.e("LOGTAG" , CREATE_PRODUCT_TABLE);

        // Execute the SQL statement

        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void deleteProduct(int product_id){
        this.getWritableDatabase().delete(ProductContract.ProductEntry.TABLE_NAME,ProductContract.ProductEntry.mID+"="+product_id,null);
    }

    public void updateProduct(int product_id,int quantity){

        ContentValues value = new ContentValues();

        value.put(ProductContract.ProductEntry.PRODUCT_QUANTITY, String.valueOf(quantity));


        this.getWritableDatabase().update(ProductContract.ProductEntry.TABLE_NAME,value,ProductContract.ProductEntry.mID
                +"="+product_id,null);
    }
}
