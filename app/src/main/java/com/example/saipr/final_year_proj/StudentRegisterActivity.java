package com.example.saipr.final_year_proj;

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

public class StudentRegisterActivity extends AppCompatActivity {
    EditText nametxt;
    EditText usntxt;
    Button reg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        nametxt=findViewById(R.id.nametxt);
        usntxt=findViewById(R.id.usntxt);
        reg=findViewById(R.id.button);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studname=nametxt.getText().toString();
                String studusn=usntxt.getText().toString();

                try {
                    URL url = new URL(RegURL.url + "Register");
                    JSONObject jsn = new JSONObject();
                    jsn.put("studname",studname);
                    jsn.put("studusn",studusn);
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(policy);
                    String response = HttpConnection.getResponse(url,jsn);
                    Toast.makeText(StudentRegisterActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
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
