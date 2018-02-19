package com.example.saipr.final_year_proj;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlacementActivity extends AppCompatActivity {
    EditText aggval;
    String aggvalue;
    Button get;
    String usn;
    TextView txtcname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);
        aggval= findViewById(R.id.aggtxt);
        usn=getIntent().getExtras().getString("usn");
        get=findViewById(R.id.compbtn);
        txtcname= findViewById(R.id.tvcname);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    txtcname.setText("");
                    aggvalue=aggval.getText().toString();
                    URL url = new URL(RegURL.url + "PlacementData");
                    JSONObject jsn = new JSONObject();
                    jsn.put("aggregate",aggvalue);
                    jsn.put("usn",usn);
                    StrictMode.ThreadPolicy pthread = new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(pthread);
                    String response = HttpConnection.getResponse(url,jsn);
                    String[] clist=response.split(":");

//
                    for(int i = 0; i<clist.length; i++)
                    {
//                        Toast.makeText(PlacementActivity.this,list.toString(),Toast.LENGTH_SHORT).show();
                        txtcname.append(clist[i]+"\n");
                    }

                    //Toast.makeText(PlacementActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
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
