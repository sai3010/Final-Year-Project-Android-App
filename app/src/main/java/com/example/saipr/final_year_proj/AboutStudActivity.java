package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class AboutStudActivity extends AppCompatActivity {
    String fname = "";
    String email = "";
    String usn = "";
    String lname,branch,address,phone,sem;
    EditText fnameet,lnameet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_stud);
        fnameet=findViewById(R.id.fnameet);
        lnameet=findViewById(R.id.lnameet);
        fname = getIntent().getExtras().getString("fname");
        email = getIntent().getExtras().getString("email");
        sem=getIntent().getExtras().getString("sem");
        branch=getIntent().getExtras().getString("branch");
        address=getIntent().getExtras().getString("address");
        phone=getIntent().getExtras().getString("phone");
        lname=getIntent().getExtras().getString("lname");
        fnameet.setText(fname);
        lnameet.setText(lname);
    }
}
