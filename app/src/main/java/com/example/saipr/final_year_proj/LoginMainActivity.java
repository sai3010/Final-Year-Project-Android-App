package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        Button std;
        std = findViewById(R.id.stdlogin);
        std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginMainActivity.this, StudentLoginActivity.class);
                startActivity(i);
            }
        });
    }
}
        /*//Toast.makeText(MainActivity.this, ":"+name+"\n:"+email+"\n:"+mob+"\n:"+address+"\n:"+pass+"\n:"+cpass, Toast.LENGTH_SHORT).show();*/