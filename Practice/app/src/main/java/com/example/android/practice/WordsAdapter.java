package com.example.android.practice;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by android on 9/4/2016.
 */
public class WordsAdapter extends ArrayAdapter<Names> {
    public WordsAdapter(Activity context, ArrayList<Names> _namesArraylist){
        super(context, 0, _namesArraylist);
    }
                        
                        

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemview = convertView;
        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.word_list,parent,false);
        }
        Names _names = getItem(position);

        TextView engWord = (TextView) listitemview.findViewById(R.id.EnglishWord);
        engWord.setText(_names.getNamesInEng());
        TextView arabicWord = (TextView) listitemview.findViewById(R.id.ArabicWord);
        arabicWord.setText(_names.getNamesInArabic());

        return listitemview;
    }
}
