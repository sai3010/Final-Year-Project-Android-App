package com.example.saipr.final_year_proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import at.markushi.ui.CircleButton;

public class StudentAggregateActivity extends AppCompatActivity {

    CircleButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_aggregate);

        btn =findViewById(R.id.circlebutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentAggregateActivity.this,"Circle Button Clicked ",Toast.LENGTH_LONG).show();
            }
        });

    }
}
