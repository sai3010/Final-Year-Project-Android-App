package com.example.saipr.final_year_proj;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FacUpdateMarks extends AppCompatActivity {
    String usn,ia;
    Spinner spinner = null,subspinner=null;
    ArrayAdapter sem,scode;
    Button getbtn;
    String strsem,strscode;
    TextView textView=null;
    LinearLayout ml;
    RadioButton rb;
    RadioGroup g;
    public static String response;
    public static String sresponse;
    private static final String TAG = FacMarksActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_update_marks);
        g = findViewById(R.id.rgrp);
        textView= findViewById(R.id.marks);
        usn=getIntent().getExtras().getString("usn");
        getbtn=findViewById(R.id.gettbn);
        spinner = findViewById(R.id.sem);
        ml=findViewById(R.id.ml);
        subspinner=findViewById(R.id.subcodes);
        int radiobuttid = g.getCheckedRadioButtonId();
        rb = findViewById(radiobuttid);
        ia=rb.getText().toString();
        new FacUpdateMarks.MyNote().execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String arr[]= response.split("#");
        String subcode[]= arr[0].split(";");
        String section[]= arr[1].split(";");
        sem = new ArrayAdapter(this,android.R.layout.simple_spinner_item,section);
        scode = new ArrayAdapter(this,android.R.layout.simple_spinner_item,subcode);
        scode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sem);
        subspinner.setAdapter(scode);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strsem = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(), strsem, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        subspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strscode = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(),strbranch, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FacUpdateMarks.GetStudInfo().execute();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sresponse= sresponse.substring(1,sresponse.length()-1);
                Toast.makeText(FacUpdateMarks.this, sresponse, Toast.LENGTH_SHORT).show();
                String usn[]= sresponse.split(",");
                for(String un:usn)
                {
                    TextView tv=new TextView(FacUpdateMarks.this);
                    tv.setText(un);
                    ml.addView(tv);
                }

            }
        });

    }
    class GetStudInfo extends AsyncTask
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            URL url = null;
            try {
                url = new URL(RegURL.url+"GetStudDetails");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
                jsn.put("sem", strsem);
                jsn.put("code", strscode);
                sresponse= HttpClientConnection.executeClient(url, jsn);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

        }
    }
    class MyNote extends AsyncTask
    {

        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL(RegURL.url+"FacMarks");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
                response= HttpClientConnection.executeClient(url, jsn);

//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
//                StrictMode.setThreadPolicy(policy);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }


    }
    public void rbclick(View v)
    {
        int radiobuttid = g.getCheckedRadioButtonId();
        rb = findViewById(radiobuttid);
        //Toast.makeText(this, "OOOOOOOOOOOOOOOOOOOOOOOOOOO--" + r.getText(), Toast.LENGTH_SHORT).show();
    }
}
