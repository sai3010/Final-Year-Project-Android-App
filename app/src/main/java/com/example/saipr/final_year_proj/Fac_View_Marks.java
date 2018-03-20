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
    Spinner spin;
    String usn,res,sem,semval;
    ArrayAdapter<Model> adapter;
    List<Model> list = new ArrayList<Model>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac__view__marks);
        listView = (ListView) findViewById(R.id.my_list);
        spin=findViewById(R.id.sem_spinner);
        ArrayAdapter<CharSequence> badapter = ArrayAdapter.createFromResource(this,
                R.array.sem_lsit, android.R.layout.simple_spinner_item);
        badapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(badapter);
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
}
