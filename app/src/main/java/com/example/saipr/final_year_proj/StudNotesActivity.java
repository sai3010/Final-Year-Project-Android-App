package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StudNotesActivity extends AppCompatActivity {
    String usn,sem;
    String response;
    LinearLayout lview;
    TextView title,fnames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_notes);
        ArrayList<String> fname=new ArrayList<>();
        lview=findViewById(R.id.lview);
        title=findViewById(R.id.title);
        usn = getIntent().getExtras().getString("usn");
        sem = getIntent().getExtras().getString("sem");
        new MyNote().execute();
        title.setText("Sem"+" "+sem);
        //Toast.makeText(StudNotesActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
    }



    class MyNote extends AsyncTask
    {


        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL(RegURL.url+"GetNotesDetails");
                JSONObject jsn = new JSONObject();
                jsn.put("sem",sem);
                jsn.put("usn", usn);
                response= HttpClientConnection.executeClient(url, jsn);
                String []f=response.split(",");
                ListView lv=new ListView(StudNotesActivity.this);
                ArrayAdapter<String>adapter=new ArrayAdapter<String>(StudNotesActivity.this,R.layout.activity_stud_notes,f);
                lv.setAdapter(adapter);
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                StrictMode.setThreadPolicy(policy);
                lview.addView(lv);
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
