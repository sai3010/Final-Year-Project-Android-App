package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnlogin;
    Button btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin= findViewById(R.id.loginbtn);
        btnreg=findViewById(R.id.regbtn);

        Toast.makeText(this, "::::::::::::::::::::::::::::::::", Toast.LENGTH_SHORT).show();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginMainActivity.class);
                startActivity(i);
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegisterMainActivity.class);
                startActivity(i);
            }
        });
    }


}
