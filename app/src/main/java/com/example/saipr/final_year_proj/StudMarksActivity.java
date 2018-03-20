package com.example.saipr.final_year_proj;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    String usn,sem;
    TextView sembox,usnbox;
    RadioButton r;
    RadioGroup g;
    Button submit;
    String ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_marks);
        usn = getIntent().getExtras().getString("usn");
        sem = getIntent().getExtras().getString("sem");
        sembox = findViewById(R.id.sembox);
        usnbox = findViewById(R.id.usnbox);


        g = findViewById(R.id.rgrp);
        int radiobuttid = g.getCheckedRadioButtonId();
        r = findViewById(radiobuttid);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usn = usnbox.getText().toString();
                sem = sembox.getText().toString();
                ia = r.getText().toString();

                if (usn.isEmpty() || sem.isEmpty() || ia.isEmpty()) {
                    Toast.makeText(StudMarksActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        fetch_section();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
    }


        public void fetch_section() throws JSONException, IOException {
        URL url = new URL(RegURL.url + "Student");
        JSONObject jsn = new JSONObject();
        jsn.put("usn", usn);
        jsn.put("sem",sem);
        jsn.put("ia",ia);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        String response = null;
        response = HttpConnection.getResponse(url, jsn);
            Toast.makeText(StudMarksActivity.this, "response", Toast.LENGTH_SHORT).show();

    }








    public void rbclick(View v) {
        int radiobuttid = g.getCheckedRadioButtonId();
        r = findViewById(radiobuttid);
        //Toast.makeText(this, "OOOOOOOOOOOOOOOOOOOOOOOOOOO--" + r.getText(), Toast.LENGTH_SHORT).show();
    }
}
