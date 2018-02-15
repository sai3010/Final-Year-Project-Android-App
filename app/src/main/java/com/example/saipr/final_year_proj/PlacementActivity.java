package com.example.saipr.final_year_proj;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlacementActivity extends AppCompatActivity {
    EditText aggval;
    String aggvalue;
    Button get;
    String usn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);
        aggval= findViewById(R.id.aggtxt);
        aggvalue=aggval.getText().toString();
        usn=getIntent().getExtras().getString("usn");
        get=findViewById(R.id.compbtn);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    URL url = new URL(RegURL.url + "PlacementData");
                    JSONObject jsn = new JSONObject();
                    jsn.put("aggregate",aggval);
                    jsn.put("usn",usn);
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(policy);
                    String response = HttpConnection.getResponse(url,jsn);
                    Toast.makeText(PlacementActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
