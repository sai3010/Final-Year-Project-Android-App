package com.example.saipr.final_year_proj;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class FacultyRegisterActivity extends AppCompatActivity
{
    RadioButton rb;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_register);
        rg=findViewById(R.id.rgrp);
        Spinner qual = findViewById(R.id.qual_drop);
        ArrayAdapter<CharSequence> myadapter = ArrayAdapter.createFromResource(this,
                R.array.qual_array, android.R.layout.simple_spinner_item);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qual.setAdapter(myadapter);
        qual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"  selected",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        );
    }
    public void rbclick(View v)
    {
        int radiobuttinid=rg.getCheckedRadioButtonId();
        rb=findViewById(radiobuttinid);
        Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
    }
}

