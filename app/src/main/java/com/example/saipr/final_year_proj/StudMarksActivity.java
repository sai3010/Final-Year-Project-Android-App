package com.example.saipr.final_year_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StudMarksActivity extends AppCompatActivity {
    String usn,sem;
    TextView sembox,usnbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_marks);
        usn=getIntent().getExtras().getString("usn");
        sem=getIntent().getExtras().getString("sem");
        sembox=findViewById(R.id.sembox);
        usnbox=findViewById(R.id.usnbox);
        sembox.setText("Sem:"+sem);
        usnbox.setText("usn:"+usn);
    }
}
