package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FacAttendanceActivity extends AppCompatActivity {
    String usn,response,str;
    ListView lv;
    String [] flist;
    StringBuilder section=new StringBuilder();
    StringBuilder subcode=new StringBuilder();
    ArrayList<String> sec=new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_attendance);
        usn=getIntent().getExtras().getString("usn");
        lv=findViewById(R.id.classlist);
        new fetchsec().execute();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response=response.trim().substring(1,response.length()-1);
        flist=response.split(",");
//        Toast.makeText(this, flist[0]+flist[1], Toast.LENGTH_SHORT).show();
         for(int i =0;i<flist.length;i=i+2)
        {
            sec.add(flist[i+1]);
            section.append(flist[i+1]);
            subcode.append(flist[i]);
        }
        //Toast.makeText(this, section.toString()+subcode.toString(), Toast.LENGTH_SHORT).show();
        adapter=new ArrayAdapter<String>(FacAttendanceActivity.this,R.layout.activity_list_view,R.id.ltxtview,sec);
        lv.setAdapter(adapter);
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                str=(String)o;//As you are using Default String Adapter
                //Toast.makeText(FacAttendanceActivity.this,str,Toast.LENGTH_SHORT).show();
                Intent i =new Intent(FacAttendanceActivity.this,FacAttendanceUpdateActivity.class);
                //i.putExtra("list",flist);
                i.putExtra("section",str);
                i.putExtra("strsec",section.toString());
                i.putExtra("strscode",subcode.toString());
                startActivity(i);
            }
        });
    }

    class fetchsec extends AsyncTask
    {


        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL(RegURL.url+"FacGetSec");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
                response= HttpClientConnection.executeClient(url, jsn);
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
}
