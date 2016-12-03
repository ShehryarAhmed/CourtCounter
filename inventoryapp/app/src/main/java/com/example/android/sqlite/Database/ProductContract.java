package com.example.android.sqlite.Database;

import android.provider.BaseColumns;

/**
 * Created by android on 11/22/2016.
 */
public class ProductContract  {

    private ProductContract(){}

    public static final class ProductEntry implements BaseColumns{

        public static final String mID = "id";

        public static final String TABLE_NAME = "product";

        public static final String PRODUCT_TITLE = "title";

        public static final String PRODUCT_QUANTITY = "quantity";

        public static final String PRODUCT_price = "price";



    }
}
