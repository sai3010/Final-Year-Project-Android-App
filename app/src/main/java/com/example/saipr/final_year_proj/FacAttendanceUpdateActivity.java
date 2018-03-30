package com.example.saipr.final_year_proj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FacAttendanceUpdateActivity extends AppCompatActivity {
    String section,flist,fullsec,fullscode;
    ArrayAdapter subj;
    Spinner subjspin;
    Button update;
    String strsubj;
    ListView lv;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_attendance_update);
        section=getIntent().getExtras().getString("section");
        fullsec=getIntent().getExtras().getString("strsec");
        //Toast.makeText(this, fullsec.trim(), Toast.LENGTH_SHORT).show();
        fullscode=getIntent().getExtras().getString("strscode");
        subjspin=findViewById(R.id.subjcode);
        lv=findViewById(R.id.attlist);
        update=findViewById(R.id.attup);
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
        List<ModelAttendance> list= new ArrayList<ModelAttendance>();
            list.add(new ModelAttendance("1rn14cs000","sai",true));
            list.add(new ModelAttendance("1rn14cs000","sai",true));
            list.add(new ModelAttendance("1rn14cs000","sai",true));

        adapter = new AttendanceAdapter(FacAttendanceUpdateActivity.this,list);
        lv.setAdapter(adapter);
        //Toast.makeText(this, fullsec+fullscode, Toast.LENGTH_SHORT).show();
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                String str = (String) o;//As you are using Default String Adapter
                Toast.makeText(FacAttendanceUpdateActivity.this, position + " "+str, Toast.LENGTH_SHORT).show();

            }
            });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}
