package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterMainActivity extends AppCompatActivity {
    Button regstud;
    Button regfac;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
        regstud=findViewById(R.id.regstud);
        regfac=findViewById(R.id.regfac);





        regstud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(RegisterMainActivity.this,StudentRegisterActivity.class);
                startActivity(intent);
            }
        });

        regfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(RegisterMainActivity.this,FacultyRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
