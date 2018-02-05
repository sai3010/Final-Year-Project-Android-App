package com.example.saipr.final_year_proj;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentRegisterActivity extends AppCompatActivity
{
    EditText nametxt;
    EditText usntxt;
    Button reg;
   RadioButton r;
   RadioGroup g;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        g=findViewById(R.id.stdrgrp);
        nametxt=findViewById(R.id.nametxt);
        usntxt=findViewById(R.id.usntxt);
        reg=findViewById(R.id.button);

        Spinner branch = findViewById(R.id.branch);

        ArrayAdapter<CharSequence> badapter = ArrayAdapter.createFromResource(this,
                R.array.branch_array, android.R.layout.simple_spinner_item);
        badapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(badapter);

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                             @Override
                                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                 Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();


                                             }

                                             @Override
                                             public void onNothingSelected(AdapterView<?> adapterView) {

                                             }
                                         });

        reg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String studname=nametxt.getText().toString();
                String studusn=usntxt.getText().toString();

                try
                {
                    URL url = new URL(RegURL.url + "Register");
                    JSONObject jsn = new JSONObject();
                    jsn.put("studname",studname);
                    jsn.put("studusn",studusn);
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(policy);
                    String response = HttpConnection.getResponse(url,jsn);
                    Toast.makeText(StudentRegisterActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    public void rbclick(View v)
    {
        int radiobuttid=g.getCheckedRadioButtonId();
        r=findViewById(radiobuttid);
        Toast.makeText(this, r.getText(), Toast.LENGTH_SHORT).show();
    }
}
