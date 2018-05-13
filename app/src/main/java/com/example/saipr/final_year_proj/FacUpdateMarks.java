package com.example.saipr.final_year_proj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacUpdateMarks extends AppCompatActivity {
    String usn,ia;
    Spinner spinner = null,subspinner=null;
    ArrayAdapter sem,scode;
    Button getbtn,updatebtn;
    static  String strsem,strscode,str;
    TextView textView=null;
    LinearLayout ml;
    RadioButton rb;
    RadioGroup g;
    ListView lv;
    ArrayList<String> section=new ArrayList<>();
    ArrayList<String> subcode=new ArrayList<>();
    public static String response;
    public static String sresponse;
    private static final String TAG = FacMarksActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_update_marks);
        g = findViewById(R.id.rgrp);
        textView= findViewById(R.id.marks);
        usn=getIntent().getExtras().getString("usn");
        getbtn=findViewById(R.id.gettbn);
        spinner = findViewById(R.id.sem);
        ml=findViewById(R.id.ml);
        subspinner=findViewById(R.id.subcodes);
        lv=findViewById(R.id.studlist);
        updatebtn=findViewById(R.id.updatebtn);
        int radiobuttid = g.getCheckedRadioButtonId();
        rb = findViewById(radiobuttid);
        ia=rb.getText().toString();
        new FacUpdateMarks.MyNote().execute();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response=response.substring(1,response.length()-1);
        String arr[]=response.split(",");
        for(int i=0;i<arr.length;i++)
        {
            if(i%2==0)
            {
                subcode.add(arr[i]);
            }
            else
            {
                section.add(arr[i]);
            }
        }
        sem = new ArrayAdapter(this,android.R.layout.simple_spinner_item,section);
        scode = new ArrayAdapter(this,android.R.layout.simple_spinner_item,subcode);
        scode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sem);
        subspinner.setAdapter(scode);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strsem = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(), strsem, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        subspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strscode = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(),strbranch, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FacUpdateMarks.GetStudInfo().execute();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sresponse= sresponse.substring(1,sresponse.length()-1);
               // Toast.makeText(FacUpdateMarks.this, sresponse, Toast.LENGTH_SHORT).show();
                String usn[]= sresponse.split(",");
                ArrayAdapter<String>adapter=new ArrayAdapter<String>(FacUpdateMarks.this,R.layout.activity_list_view,R.id.ltxtview,usn);
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                StrictMode.setThreadPolicy(policy);
                final HashMap<String,String> hmap= new HashMap<>();
                lv.setAdapter(adapter);
                lv.setClickable(true);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                        Object o = lv.getItemAtPosition(position);
                        str=(String)o;//As you are using Default String Adapter
                       // Toast.makeText(FacUpdateMarks.this,position+"",Toast.LENGTH_SHORT).show();
                        final EditText txt = new EditText(FacUpdateMarks.this);
                        AlertDialog.Builder builder = new AlertDialog.Builder(FacUpdateMarks.this);
                        builder.setTitle("Enter Marks");
                        builder.setView(txt);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = txt.getText().toString();
                                //Toast.makeText(FacUpdateMarks.this,name,Toast.LENGTH_SHORT).show();
                                hmap.put(str,name);
                        }
                    });



                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                });
            updatebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<Map.Entry<String, String>> arrayList = new ArrayList<>();
                    arrayList.addAll(hmap.entrySet());
                    Log.i("qwerty  ",arrayList.toString());
                    //Toast.makeText(FacUpdateMarks.this, "\n"+arrayList.toString(), Toast.LENGTH_SHORT).show();
                    URL url = null;
                    try {
                        url = new URL(RegURL.url+"FacUpdateMarks");
                        JSONObject jsn= new JSONObject();
                        jsn.put("upmarks",arrayList);
                        jsn.put("sem",strsem);
                        jsn.put("scode",strscode);
                        jsn.put("ia",ia);
                        response= HttpClientConnection.executeClient(url, jsn);
                       Toast.makeText(FacUpdateMarks.this, "Marks Updated", Toast.LENGTH_SHORT).show();
                        updatebtn.setClickable(false);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            }
        });

    }
    class GetStudInfo extends AsyncTask
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            URL url = null;
            try {
                url = new URL(RegURL.url+"GetStudDetails");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
                jsn.put("sem", strsem);
                jsn.put("code", strscode);
                sresponse= HttpClientConnection.executeClient(url, jsn);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

        }
    }
    class MyNote extends AsyncTask
    {

        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL(RegURL.url+"FacMarks");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
                response= HttpClientConnection.executeClient(url, jsn);

//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
//                StrictMode.setThreadPolicy(policy);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }


    }
    public void rbclick(View v)
    {
        int radiobuttid = g.getCheckedRadioButtonId();
        rb = findViewById(radiobuttid);
        //Toast.makeText(this, "OOOOOOOOOOOOOOOOOOOOOOOOOOO--" + r.getText(), Toast.LENGTH_SHORT).show();
    }
}
