package com.example.android.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by android on 9/4/2016.
 */
public class Allah_Names extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        ArrayList<Names> names = new ArrayList<Names>();
        names.add(new Names("Allah","الله"));
        names.add(new Names("Ar-Rahman","الرحمن"));
        names.add(new Names("Ar-Rahim","الرحيم"));
        names.add(new Names("Al-Malik","الملك"));
        names.add(new Names("Al-Quddus","القدوس"));
        names.add(new Names("As-Salam","السلام"));
        names.add(new Names("Al-Mu'min","المؤمن"));
        names.add(new Names("Al-Muhaymin","المهيمن"));
        names.add(new Names("Al-Aziz ","العزيز"));
        names.add(new Names("Al-Jabbar ","الجبار"));
        names.add(new Names("Al-Mutakabbir ","المتكبر"));
        names.add(new Names("Al-Khaliq","الخالق"));
        names.add(new Names("Al-Bari","البارئ"));





        WordsAdapter wordsAdapter = new WordsAdapter(this,names);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);
    }
}
