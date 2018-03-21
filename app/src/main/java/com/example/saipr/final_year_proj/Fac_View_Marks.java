package com.example.saipr.final_year_proj;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Fac_View_Marks extends AppCompatActivity {
    
    ListView listView;
    Button btnSave;
    Spinner spin,semspin;
    String usn,res,sem,semval,scode;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();
    ArrayList <String> subject= new ArrayList<>();
    ArrayList <String> sec= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac__view__marks);
       usn= getIntent().getExtras().getString("usn");
       btnSave=findViewById(R.id.go);
        new fetchdetails().execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Toast.makeText(Fac_View_Marks.this,res,Toast.LENGTH_SHORT).show();
        res=res.substring(1,res.length()-1);
        String [] arr= res.split(",");

        for(int i=0;i<arr.length;i++)
        {
            if(i%2==0)
            {
                subject.add(arr[i]);

            }
            else
            {
                sec.add(arr[i]);
            }
        }
        //Toast.makeText(Fac_View_Marks.this,subject.toString()+"\t"+sec.toString(),Toast.LENGTH_LONG).show();

        //Toast.makeText(Fac_View_Marks.this,res1,Toast.LENGTH_LONG).show();



        listView = (ListView) findViewById(R.id.my_list);
        spin=findViewById(R.id.sem_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        sec); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spin.setAdapter(spinnerArrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semval = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        semspin=findViewById(R.id.subjectcode_spinner);
        ArrayAdapter<String> scodeadapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        subject); //selected item will look like a spinner set from XML
        scodeadapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        semspin.setAdapter(scodeadapter);
        semspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scode = parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            new fetchstud().execute();
            }
        });

        adapter = new MyAdapter(this,getModel());
        listView.setAdapter(adapter);
    }
    private List<Model> getModel() {
        list.add(new Model("1RN14CS001","11  23  23"));
        list.add(new Model("1RN14CS002","22"));
        list.add(new Model("1RN14CS003","33"));
        list.add(new Model("1RN14CS004","44"));
        list.add(new Model("1RN14CS005","55"));
        list.add(new Model("1RN14CS006","66"));
        list.add(new Model("1RN14CS007","77"));
        list.add(new Model("1RN14CS008","88"));
        list.add(new Model("1RN14CS009","99"));
        list.add(new Model("1RN14CS010","78"));
        list.add(new Model("1RN14CS011","45"));
        list.add(new Model("1RN14CS012","89"));

        return list;
    }
    public class fetchdetails extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = new URL(RegURL.url + "FacViewMarks");
                JSONObject jsn = new JSONObject();
                jsn.put("usn", usn);
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
    public class fetchstud extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = new URL(RegURL.url + "StudDetails");
                JSONObject jsn = new JSONObject();
                jsn.put("scode",scode );
                jsn.put("section",semval );
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
