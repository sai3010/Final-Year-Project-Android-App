package com.example.saipr.final_year_proj;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentRegisterActivity extends AppCompatActivity {
    EditText fnametxt;
    EditText lnametext;
    EditText usntxt;
    EditText sem;
    EditText dob;
    EditText email;
    EditText pass;
    EditText cpass;
    EditText phone;
    EditText address;
    Button reg;
    RadioButton r;
    RadioGroup g;
    String studfname,studlname, studusn, studsem, studemail, studaddress, studpass;
    String studcpass, studphone, studdob, studgender;
    String branch;
    Spinner spinner = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        g = findViewById(R.id.rgrp);
        fnametxt = findViewById(R.id.fnametxt);
        lnametext=findViewById(R.id.lnametxt);
        usntxt = findViewById(R.id.usntxt);
        sem = findViewById(R.id.studsem);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.emailtxt);
        pass = findViewById(R.id.stdpwd);
        cpass = findViewById(R.id.stdcpwd);
        phone = findViewById(R.id.phonetxt);
        address = findViewById(R.id.addresstxt);
        reg = findViewById(R.id.button);
        int radiobuttid = g.getCheckedRadioButtonId();
        r = findViewById(radiobuttid);

        spinner = findViewById(R.id.branch);
        Toast.makeText(this, spinner + "", Toast.LENGTH_SHORT).show();

        ArrayAdapter<CharSequence> badapter = ArrayAdapter.createFromResource(this,
                R.array.branch_array, android.R.layout.simple_spinner_item);
        badapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(badapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studfname = fnametxt.getText().toString();
                studlname=lnametext.getText().toString();
                studusn = usntxt.getText().toString();
                studsem = sem.getText().toString();
                studemail = email.getText().toString();
                studaddress = address.getText().toString();
                studpass = pass.getText().toString();
                studcpass = cpass.getText().toString();
                studphone = phone.getText().toString();
                studdob = dob.getText().toString();
                studgender = r.getText().toString();

                /*Toast.makeText(StudentRegisterActivity.this, studfname+"\n"+studusn+"\n"+studsem+"\n"+branch+"\n"+studgender+"\n"+studemail+"\n"+
                        ""+studpass+"\n"+studcpass+"\n"+studphone+"\n"+studaddress, Toast.LENGTH_LONG).show();*/

                if(studfname.isEmpty()||studlname.isEmpty()||studusn.isEmpty()||studsem.isEmpty()||studemail.isEmpty()||studaddress.isEmpty()||studpass.isEmpty()||studcpass.isEmpty()
                        ||studphone.isEmpty()||studdob.isEmpty()){
                    Toast.makeText(StudentRegisterActivity.this,"Fill in all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!studpass.equals(studcpass))
                    {
                        pass.setError("Password Mismatch");
                        cpass.setError("Password Mismatch");
                    }
                    else
                    {
                        try {
                            send_data();
                            Toast.makeText(StudentRegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });
    }

    public void send_data() throws IOException, JSONException {
        URL url = new URL(RegURL.url + "StudentRegister");
        JSONObject jsn = new JSONObject();
        jsn.put("studfname", studfname);
        jsn.put("studlname", studlname);
        jsn.put("studusn", studusn);
        jsn.put("studsem", studsem);
        jsn.put("studemail", studemail);
        jsn.put("studaddress", studaddress);
        jsn.put("studpass", studpass);
        jsn.put("studcpass", studcpass);
        jsn.put("studphone", studphone);
        jsn.put("studdob", studdob);
        jsn.put("studgender", studgender);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        String response = null;
        response = HttpConnection.getResponse(url, jsn);
        Toast.makeText(StudentRegisterActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
    }

    public void rbclick(View v) {
        int radiobuttid = g.getCheckedRadioButtonId();
        r = findViewById(radiobuttid);
        //Toast.makeText(this, "OOOOOOOOOOOOOOOOOOOOOOOOOOO--" + r.getText(), Toast.LENGTH_SHORT).show();
    }
}

