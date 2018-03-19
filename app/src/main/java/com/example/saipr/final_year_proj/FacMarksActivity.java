package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacMarksActivity extends AppCompatActivity {
    String usn;
    TextView textView = null;
    Button vbtn,ubtn;
    private static final String TAG = FacMarksActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_marks);
        textView = findViewById(R.id.marks);
        usn = getIntent().getExtras().getString("usn");
        vbtn=findViewById(R.id.vbtn);
        ubtn=findViewById(R.id.upbtn);

        vbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FacMarksActivity.this,Fac_View_Marks.class);
                startActivity(i);
            }
        });
        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(FacMarksActivity.this,FacUpdateMarks.class);
                i.putExtra("usn",usn);
                startActivity(i);
            }
        });

    }
}
