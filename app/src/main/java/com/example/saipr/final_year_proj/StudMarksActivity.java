package com.example.saipr.final_year_proj;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StudMarksActivity extends AppCompatActivity {
    String usn, sem;
    Button submit;
    String ia,res;
    ArrayAdapter<Model> adapter;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_marks);
        usn = getIntent().getExtras().getString("usn");
        sem = getIntent().getExtras().getString("sem");
        listView=findViewById(R.id.my_list);

        new fetch_section().execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Toast.makeText(StudMarksActivity.this,res, Toast.LENGTH_LONG).show();


        Toast.makeText(StudMarksActivity.this, res, Toast.LENGTH_LONG).show();
        Toast.makeText(this, ""+res, Toast.LENGTH_SHORT).show();
        res= res.substring(1,res.length()-1);
        Toast.makeText(this, "<><><>\n"+res, Toast.LENGTH_SHORT).show();
        String arr[]= res.trim().split(",");

        List<Model> list= new ArrayList<Model>();
        for(int i=0; i < arr.length; i+=4) {
            list.add(new Model(""+arr[i].trim(),arr[i+1]+"\t"+arr[i+2]+"\t"+arr[i+3]));
        }

        adapter = new MyAdapter(StudMarksActivity.this,list);
        listView.setAdapter(adapter);


    }

    public class fetch_section extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = new URL(RegURL.url + "Student");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
                jsn.put("sem",sem);
                jsn.put("ia",ia);
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

    }
}
