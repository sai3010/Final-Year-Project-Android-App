package com.example.saipr.final_year_proj;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

public class FacultyRegisterActivity extends AppCompatActivity {
    EditText fnametxt;
    EditText lnametext;
    EditText usntxt;
    EditText email;
    EditText pass;
    EditText cpass;
    EditText phone;
    EditText address;
    TextView dob;
    RadioButton rb;
    RadioGroup rg;
    TextView tv;
    Calendar mCurrentDate;
    String studfname,studlname, studusn, studsem, studemail, studaddress, studpass;
    String studcpass, studphone, studdob, studgender;
    int day,month,year;
    int radiobuttinid;
    String qualification="";
    String fbranch="";
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_register);
        rg = findViewById(R.id.rgrp);
        radiobuttinid = rg.getCheckedRadioButtonId();
        rb = findViewById(radiobuttinid);
        fnametxt = findViewById(R.id.fnametxt);
        lnametext=findViewById(R.id.lnametxt);
        usntxt = findViewById(R.id.usntxt);
        email = findViewById(R.id.emailtxt);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
        phone = findViewById(R.id.phonetxt);
        address = findViewById(R.id.addresstxt);
        button = findViewById(R.id.button);
        dob=findViewById(R.id.textView);
        Spinner branch=findViewById(R.id.branch);
        ArrayAdapter<CharSequence> badapter = ArrayAdapter.createFromResource(this,
                R.array.branch_array, android.R.layout.simple_spinner_item);
        badapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(badapter);
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                           @Override
                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                               fbranch= parent.getItemAtPosition(position).toString();
                                               //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
                                           }

                                           @Override
                                           public void onNothingSelected(AdapterView<?> adapterView) {
                                               //Toast.makeText(FacultyRegisterActivity.this, ":::::::::::::::::", Toast.LENGTH_LONG);
                                           }
                                       }
        );

        Spinner qual = findViewById(R.id.qual_drop);
        ArrayAdapter<CharSequence> myadapter = ArrayAdapter.createFromResource(this,
                R.array.qual_array, android.R.layout.simple_spinner_item);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qual.setAdapter(myadapter);
        qual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                           @Override
                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                               qualification= parent.getItemAtPosition(position).toString();
                                               //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
                                           }

                                           @Override
                                           public void onNothingSelected(AdapterView<?> adapterView) {
                                               //Toast.makeText(FacultyRegisterActivity.this, ":::::::::::::::::", Toast.LENGTH_LONG);
                                           }
                                       }
        );

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if(phone.getText().length()>10)
                {
                    phone.setError("Invalid Number");
                }
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if(pass.getText().length()<6)
                {
                    pass.setError("Password must be minimum 6 char");
                }
            }
        });


        tv= (TextView) findViewById(R.id.textView);
        mCurrentDate= Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month=mCurrentDate.get(Calendar.MONTH);
        year=mCurrentDate.get(Calendar.YEAR);

        month= month+1;
        tv.setText(day+"/"+month+"/"+year);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FacultyRegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear=monthOfYear+1;
                        tv.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studfname = fnametxt.getText().toString();
                studlname=lnametext.getText().toString();
                studusn = usntxt.getText().toString().toUpperCase();
                studemail = email.getText().toString();
                studaddress = address.getText().toString();
                studpass = pass.getText().toString();
                studcpass = cpass.getText().toString();
                studphone = phone.getText().toString();
                studdob = dob.getText().toString();
                studgender = rb.getText().toString();

                if(studfname.isEmpty()||studlname.isEmpty()||studusn.isEmpty()||studemail.isEmpty()||studaddress.isEmpty()||studpass.isEmpty()||studcpass.isEmpty()
                        ||studphone.isEmpty()||studdob.isEmpty())
                {
                    Toast.makeText(FacultyRegisterActivity.this,"Fill in all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!studpass.equals(studcpass)) {
                        pass.setError("Password Mismatch");
                        cpass.setError("Password Mismatch");
                    } else {
                        try {
                            send_data();
                            //Toast.makeText(FacultyRegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });

    }

    public void rbclick(View v) {
        radiobuttinid = rg.getCheckedRadioButtonId();
        rb = findViewById(radiobuttinid);
        //Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

    public void send_data() throws IOException, JSONException {
        URL url = new URL(RegURL.url + "FacultyRegister");
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
        jsn.put("studqual",qualification);
        jsn.put("studbranch",fbranch);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        String response = null;
        response = HttpConnection.getResponse(url, jsn);
        if(response.equalsIgnoreCase("ok"))
        {
            Intent intennt = new Intent(FacultyRegisterActivity.this,LoginMainActivity.class);
            startActivity(intennt);
            Toast.makeText(FacultyRegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        }
        else if(response.equalsIgnoreCase("eok"))
        {
            Toast.makeText(this, "DB Error Contact Admin", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(FacultyRegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
        }
    }

}

