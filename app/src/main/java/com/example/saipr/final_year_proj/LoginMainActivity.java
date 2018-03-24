package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.content.SharedPreferences;
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
import java.net.Socket;
import java.net.URL;

public class LoginMainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    EditText usr;
    EditText pwd;
    TextView studname,studemail;
    Button lbtn;
    Intent intent = null;
    String usn,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        usr=findViewById(R.id.usn);
        pwd=findViewById(R.id.pw);
        lbtn=findViewById(R.id.loginbtn);

        /*Socket Skt;
        String host = "localhost";
        try {
            Skt = new Socket(host, 8084);
            Toast.makeText(LoginMainActivity.this,Skt.toString(),Toast.LENGTH_LONG);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*to store username and password*/
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);
        //Toast.makeText(this, username+password, Toast.LENGTH_SHORT).show();
        if (username == null || password == null) {
            getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                    .edit()
                    .putString(PREF_USERNAME, usr.getText().toString())
                    .putString(PREF_PASSWORD, pwd.getText().toString())
                    .apply();
        }
        else
        {
            usr.setText(username);
            pwd.setText(password);
        }

        //add the function to connect to database
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                        .edit()
                        .putString(PREF_USERNAME, usr.getText().toString())
                        .putString(PREF_PASSWORD, pwd.getText().toString())
                        .apply();
                usn=usr.getText().toString().toUpperCase();
                pass=pwd.getText().toString();
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
                    //Toast.makeText(LoginMainActivity.this,response,Toast.LENGTH_LONG).show();
                    if(res[0].equalsIgnoreCase("sok"))
                    {
                        Toast.makeText(LoginMainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        intent =new Intent(LoginMainActivity.this,StudentDashboardActivity.class);
                        intent.putExtra("password",res[1]);
                        intent.putExtra("name",res[2]);
                        intent.putExtra("email",res[3]);
                        intent.putExtra("usn",res[4]);
                        intent.putExtra("lname",res[5]);
                        intent.putExtra("sem",res[6]);
                        intent.putExtra("address",res[7]);
                        intent.putExtra("phone",res[8]);
                        intent.putExtra("branch",res[9]);
                        startActivity(intent);
                    }
                    if(res[0].equalsIgnoreCase("fok"))
                    {
                        Toast.makeText(LoginMainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        intent =new Intent(LoginMainActivity.this,FacultyDashboardActivity.class);
                        intent.putExtra("password",res[1]);
                        intent.putExtra("name",res[2]);
                        intent.putExtra("email",res[3]);
                        intent.putExtra("usn",res[4]);
                        intent.putExtra("lname",res[5]);
                        intent.putExtra("qual",res[6]);
                        intent.putExtra("address",res[7]);
                        intent.putExtra("phone",res[8]);
                        intent.putExtra("branch",res[9]);
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