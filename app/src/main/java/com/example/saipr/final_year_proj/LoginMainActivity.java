package com.example.saipr.final_year_proj;

import android.content.Intent;
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

public class LoginMainActivity extends AppCompatActivity {

    EditText usr;
    EditText pwd;
    TextView studname,studemail;
    Button lbtn;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        usr=findViewById(R.id.usn);
        pwd=findViewById(R.id.pw);
        lbtn=findViewById(R.id.loginbtn);


        //add the function to connect to database
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usn=usr.getText().toString().toUpperCase();
                String pass=pwd.getText().toString();
                try
                {
                    URL url = new URL(RegURL.url + "Login");
                    JSONObject jsn = new JSONObject();
                    jsn.put("usn",usn);
                    jsn.put("pass",pass);
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(policy);
                    String response = HttpConnection.getResponse(url,jsn);
                    String res[]=response.split("\t");
                    if(res[0].equalsIgnoreCase("sok"))
                    {
                        Toast.makeText(LoginMainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        intent =new Intent(LoginMainActivity.this,StudentDashboardActivity.class);
                        intent.putExtra("name",res[1]);
                        intent.putExtra("email",res[2]);
                        intent.putExtra("usn",res[3]);
                        startActivity(intent);
                    }
                    if(response.equalsIgnoreCase("fok"))
                    {
                        Toast.makeText(LoginMainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        intent =new Intent(LoginMainActivity.this,FacultyDashboardActivity.class);
                        startActivity(intent);
                    }
                    if(response.equalsIgnoreCase("notok"))
                    {
                        Toast.makeText(LoginMainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                        intent =new Intent(LoginMainActivity.this,LoginMainActivity.class);
                        startActivity(intent);
                    }
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