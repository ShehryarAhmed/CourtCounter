package com.example.android.sqlite.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.sqlite.Model.ProductDetail;
import com.example.android.sqlite.R;

import java.util.ArrayList;

/**
 * Created by android on 11/24/2016.
 */
public class ProductDisplayAdapter extends ArrayAdapter<ProductDetail> {

    public ProductDisplayAdapter(Activity activity, ArrayList<ProductDetail> productArrayList){
        super(activity,0,productArrayList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.listview,parent,false);
        }
        ProductDetail mproductDetail = getItem(position);

        TextView mtitleView = (TextView) view.findViewById(R.id.titleview);

        mtitleView.setText(""+mproductDetail.getMproduct_title());

        TextView mquantityView = (TextView) view.findViewById(R.id.price);

        mquantityView.setText(""+mproductDetail.getMproduct_quantity());

        return view;
    }

}
