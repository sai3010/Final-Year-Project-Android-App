package com.example.saipr.final_year_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Fac_View_Marks extends AppCompatActivity {
    
    ListView listView;
    Button btnSave;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac__view__marks);
        listView = (ListView) findViewById(R.id.my_list);
        adapter = new MyAdapter(this,getModel());
        listView.setAdapter(adapter);
    }
    private List<Model> getModel() {
        list.add(new Model("1RN14CS001","11  23  23"));
        list.add(new Model("1RN14CS002","22"));
        list.add(new Model("1RN14CS003","33"));
        list.add(new Model("1RN14CS004","44"));
        list.add(new Model("1RN14CS005","55"));
        list.add(new Model("1RN14CS006","66"));
        list.add(new Model("1RN14CS007","77"));
        list.add(new Model("1RN14CS008","88"));
        list.add(new Model("1RN14CS009","99"));
        list.add(new Model("1RN14CS010","78"));
        list.add(new Model("1RN14CS011","45"));
        list.add(new Model("1RN14CS012","89"));

        return list;
    }
}
