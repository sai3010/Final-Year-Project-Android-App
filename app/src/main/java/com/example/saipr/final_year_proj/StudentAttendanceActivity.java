package com.example.saipr.final_year_proj;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceActivity extends AppCompatActivity {
   static String susn,ssem;
   String res;
   ProgressDialog dialog;
   ListView lv;
    ArrayAdapter<Model> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        susn=getIntent().getExtras().getString("usn");
        ssem=getIntent().getExtras().getString("sem");
        lv=findViewById(R.id.atlist);
        //dialog = ProgressDialog.show(StudentAttendanceActivity.this, "", "Uploading File...", true);

        new fetch().execute();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
//        String [] val=res.split(" ");
//        List<Model> list= new ArrayList<Model>();
//        for(int i=0; i < val.length; i=i+2) {
//            list.add(new Model(""+val[i].trim(),val[i+1]));
//        }
   //     adapter = new MyAdapter(StudentAttendanceActivity.this,list);
       // lv.setAdapter(adapter);
    }
    public class fetch extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = new URL(RegURL.url + "getStudAtt");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", susn);
                jsn.put("sem", ssem);
                res = HttpClientConnection.executeClient(url, jsn);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("null"))
                Toast.makeText(StudentAttendanceActivity.this, "No Data Received", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                String[] val = s.split(" ");
                List<Model> list = new ArrayList<Model>();
                for (int i = 0; i < val.length; i = i + 2) {
                    list.add(new Model("" + val[i].trim(), val[i + 1]));
                }
                adapter = new MyAdapter(StudentAttendanceActivity.this, list);
                lv.setAdapter(adapter);
            }
        }
    }
}
