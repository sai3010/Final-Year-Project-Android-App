package com.example.saipr.final_year_proj;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class StudMarksActivity extends AppCompatActivity {
    String usn, sem;
    EditText sembox;
    EditText usnbox;
    RadioButton r;
    RadioGroup g;
    Button submit;
    String ia,res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_marks);
      usn = getIntent().getExtras().getString("usn");
        sem = getIntent().getExtras().getString("sem");
        submit=findViewById(R.id.submit);
        g = findViewById(R.id.rgrp);
       int radiobuttid = g.getCheckedRadioButtonId();
        r = findViewById(radiobuttid);
        new fetch_section().execute();


    submit.setOnClickListener(new View.OnClickListener() {
        @Override

       public void onClick(View view) {


          }
     });
   }

    public class fetch_section extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = new URL(RegURL.url + "Student");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
                jsn.put("sem",sem);
                jsn.put("ia",ia);
                res = HttpClientConnection.executeClient(url, jsn);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }

    }


    public void rbclick(View v) {
        int radiobuttid = g.getCheckedRadioButtonId();
        r = findViewById(radiobuttid);
        //Toast.makeText(this, "OOOOOOOOOOOOOOOOOOOOOOOOOOO--" + r.getText(), Toast.LENGTH_SHORT).show();
    }
}
