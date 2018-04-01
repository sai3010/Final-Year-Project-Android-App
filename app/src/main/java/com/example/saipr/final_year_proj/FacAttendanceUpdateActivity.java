package com.example.saipr.final_year_proj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
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

public class FacAttendanceUpdateActivity extends AppCompatActivity {
    String section,facusn,fullsec,fullscode;
    ArrayAdapter subj;
    Spinner subjspin;
    Button update;
    String strsubj;
    static String resp;
    ListView lv;
    ArrayList<String>finalarr=new ArrayList<>();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fac_attendance_update);
        section=getIntent().getExtras().getString("section");
        fullsec=getIntent().getExtras().getString("strsec");
        facusn=getIntent().getExtras().getString("usn");
        //Toast.makeText(this, fullsec.trim(), Toast.LENGTH_SHORT).show();
        fullscode=getIntent().getExtras().getString("strscode");
        subjspin=findViewById(R.id.subjcode);
        lv=findViewById(R.id.attlist);
        update=findViewById(R.id.attup);
        String [] sec=fullsec.trim().split(" ");
        String [] sub=fullscode.trim().split(" ");
        //Toast.makeText(this, section, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, sec[0]+sec[1], Toast.LENGTH_SHORT).show();
        subj = new ArrayAdapter(FacAttendanceUpdateActivity.this,android.R.layout.simple_spinner_item,sub);
        subj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjspin.setAdapter(subj);
        subjspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strsubj = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getBaseContext(), strsubj, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        List<ModelAttendance> list= new ArrayList<ModelAttendance>();
//            list.add(new ModelAttendance("1rn14cs000","sai",true));
//            list.add(new ModelAttendance("1rn14cs000","sai",true));
//            list.add(new ModelAttendance("1rn14cs000","sai",true));
        new fetchtempatt().execute();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, resp, Toast.LENGTH_SHORT).show();
        String [] l={"usn 1","usn2"};
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setTextFilterEnabled(true);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,l);
//        adapter = new AttendanceAdapter(FacAttendanceUpdateActivity.this,list);
        lv.setAdapter(adapter);
        //Toast.makeText(this, fullsec+fullscode, Toast.LENGTH_SHORT).show();
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                String str = (String) o;//As you are using Default String Adapter
                CheckedTextView item = (CheckedTextView) arg1;
                Toast.makeText(FacAttendanceUpdateActivity.this, lv.getCount()+""+item.isChecked()+""+position + " "+str, Toast.LENGTH_SHORT).show();

            }
            });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<lv.getCount();i++)
                {
                    if(lv.isItemChecked(i))
                    {
                        Toast.makeText(FacAttendanceUpdateActivity.this,lv.getAdapter().getItem(i)+"\t"+"ture", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(FacAttendanceUpdateActivity.this,lv.getAdapter().getItem(i)+"", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public class fetchtempatt extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = new URL(RegURL.url + "GetTempAttendance");
                JSONObject jsn = new JSONObject();
                jsn.put("section", section);
                jsn.put("usn", facusn);
                resp = HttpClientConnection.executeClient(url, jsn);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return resp;
        }

    }
}
