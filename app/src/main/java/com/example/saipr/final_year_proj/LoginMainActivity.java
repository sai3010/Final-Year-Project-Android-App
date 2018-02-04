package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginMainActivity extends AppCompatActivity {

    EditText usr;
    EditText pwd;
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        usr=findViewById(R.id.usn);
        pwd=findViewById(R.id.pw);
        submitbtn=findViewById(R.id.plogin);

        //add the function to connect to database
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=usr.getText().toString();
                String pass=pwd.getText().toString();
                try {
                    URL url = new URL(RegURL.url + "Register");
                    JSONObject jsn = new JSONObject();
                    jsn.put("usr",usr);
                    jsn.put("pwd",pwd);
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(policy);
                    String response = HttpConnection.getResponse(url,jsn);
                    Toast.makeText(LoginMainActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
        /*//Toast.makeText(MainActivity.this, ":"+name+"\n:"+email+"\n:"+mob+"\n:"+address+"\n:"+pass+"\n:"+cpass, Toast.LENGTH_SHORT).show();*/