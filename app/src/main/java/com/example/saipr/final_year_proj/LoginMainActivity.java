package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginMainActivity extends AppCompatActivity {

    EditText etusr;
    EditText etpwd;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        etusr=findViewById(R.id.usn);
        etpwd=findViewById(R.id.pw);
        sub=findViewById(R.id.plogin);

        //add the function to connect to database


    }
}
        /*//Toast.makeText(MainActivity.this, ":"+name+"\n:"+email+"\n:"+mob+"\n:"+address+"\n:"+pass+"\n:"+cpass, Toast.LENGTH_SHORT).show();*/