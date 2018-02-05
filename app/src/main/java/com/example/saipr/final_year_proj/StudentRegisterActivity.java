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
    EditText nametxt;
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
    String studname, studusn, studsem, studemail, studaddress, studpass;
    String studcpass, studphone, studdob, studgender;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        g = findViewById(R.id.stdrgrp);
        nametxt = findViewById(R.id.nametxt);
        usntxt = findViewById(R.id.usntxt);
        sem = findViewById(R.id.stdsem);
        dob = findViewById(R.id.dobtxt);
        email = findViewById(R.id.emailtxt);
        pass = findViewById(R.id.stdpwd);
        cpass = findViewById(R.id.stdcpwd);
        phone = findViewById(R.id.phonetxt);
        address = findViewById(R.id.addresstxt);
        reg = findViewById(R.id.button);

        final Spinner branch = findViewById(R.id.branch);
        Toast.makeText(this, branch + "", Toast.LENGTH_SHORT).show();

        ArrayAdapter<CharSequence> badapter = ArrayAdapter.createFromResource(this,
                R.array.branch_array, android.R.layout.simple_spinner_item);
        badapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(badapter);

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studname = nametxt.getText().toString();
                studusn = usntxt.getText().toString();
                studsem = sem.getText().toString();
                studemail = email.getText().toString();
                studaddress = address.getText().toString();
                studpass = pass.getText().toString();
                studcpass = cpass.getText().toString();
                studphone = phone.getText().toString();
                studdob = dob.getText().toString();
                studgender = r.getText().toString();


//                if (studname.isEmpty() && studusn.isEmpty() && studsem.isEmpty() && studemail.isEmpty() && studaddress.isEmpty() && studphone.isEmpty() && studdob.isEmpty() &&  studgender.isEmpty()) {
//                    Toast.makeText(StudentRegisterActivity.this, "________________________", Toast.LENGTH_SHORT).show();
//                    if (studpass.equals(studcpass) && studpass.isEmpty()) {
//                        try {
//                            Toast.makeText(StudentRegisterActivity.this, "iiiiii--- "+studpass+"  --jjjjj", Toast.LENGTH_SHORT).show();
//                            send_data();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        Toast.makeText(StudentRegisterActivity.this, "Passwords Miss Match", Toast.LENGTH_SHORT);
//                    }
//                } else {
//                    Toast.makeText(StudentRegisterActivity.this, "Fill all the Fields!!", Toast.LENGTH_SHORT);
//                }

                Toast.makeText(StudentRegisterActivity.this, studgender + "\t" + branch, Toast.LENGTH_SHORT).show();
                Toast.makeText(StudentRegisterActivity.this, studgender.isEmpty() + "", Toast.LENGTH_SHORT).show();
                if (studname.isEmpty() && studusn.isEmpty() && studsem.isEmpty() && studemail.isEmpty() && studaddress.isEmpty() && studphone.isEmpty() && studdob.isEmpty() && studpass.isEmpty()) {
                    Toast.makeText(StudentRegisterActivity.this, "Some of the fields are empty...", Toast.LENGTH_SHORT).show();
                } else {
                    if (!studgender.isEmpty()) {
                        if (!studpass.isEmpty()) {
                            if (studpass.equals(studcpass)) {
                                try {
                                    send_data();
                                    Toast.makeText(StudentRegisterActivity.this, "Sucessfully Registered", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(StudentRegisterActivity.this, "Passwords Miss Match", Toast.LENGTH_SHORT);
                            }

                        } else {
                            Toast.makeText(StudentRegisterActivity.this, "Password field is empty", Toast.LENGTH_SHORT);
                        }
                    } else {
                        Toast.makeText(StudentRegisterActivity.this, "Select Your Gender", Toast.LENGTH_SHORT);
                    }
                }

            }
        });
    }

    public void send_data() throws IOException, JSONException {
        URL url = new URL(RegURL.url + "StudentRegister");
        JSONObject jsn = new JSONObject();
        jsn.put("studname", studname);
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
        Toast.makeText(this, "OOOOOOOOOOOOOOOOOOOOOOOOOOO--" + r.getText(), Toast.LENGTH_SHORT).show();
    }
}

