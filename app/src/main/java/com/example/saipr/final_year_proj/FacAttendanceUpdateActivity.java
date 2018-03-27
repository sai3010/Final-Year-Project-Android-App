package com.example.saipr.final_year_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FacAttendanceUpdateActivity extends AppCompatActivity {
    String section,flist,fullsec,fullscode;
    ArrayAdapter subj;
    Spinner subjspin;
    String strsubj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_attendance_update);
        section=getIntent().getExtras().getString("section");
        fullsec=getIntent().getExtras().getString("strsec");
        //Toast.makeText(this, fullsec.trim(), Toast.LENGTH_SHORT).show();
        fullscode=getIntent().getExtras().getString("strscode");
        subjspin=findViewById(R.id.subjcode);
        String [] sec=fullsec.trim().split(" ");
        String [] sub=fullscode.trim().split(" ");
        //Toast.makeText(this, sec[0]+sec[1], Toast.LENGTH_SHORT).show();
        subj = new ArrayAdapter(FacAttendanceUpdateActivity.this,android.R.layout.simple_spinner_item,sub);
        subj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjspin.setAdapter(subj);
        subjspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strsubj = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(), strsubj, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Toast.makeText(this, fullsec+fullscode, Toast.LENGTH_SHORT).show();
    }
}
