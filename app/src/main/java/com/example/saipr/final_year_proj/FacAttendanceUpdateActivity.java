package com.example.saipr.final_year_proj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FacAttendanceUpdateActivity extends AppCompatActivity {
    String section,facusn,fullsec,fullscode;
    ArrayAdapter subj;
    Spinner subjspin;
    Button update;
    String strsubj,imgString,ires;
    Button cap;
    private static final int CAMERA_REQUEST = 1888;
    static String resp;
    ListView lv;
    StringBuilder s=new StringBuilder();
    HashMap<String,String> hmap= new HashMap<>();
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
        cap=findViewById(R.id.capture);
        //update=findViewById(R.id.attup);
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
        resp=resp.substring(1,resp.length()-1);
        String [] attlist=resp.split(",");
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setTextFilterEnabled(true);
        adapter=new ArrayAdapter<String>(this,R.layout.simple_checked_list_item,attlist);
//        adapter = new AttendanceAdapter(FacAttendanceUpdateActivity.this,list);
        lv.setAdapter(adapter);
        //Toast.makeText(this, fullsec+fullscode, Toast.LENGTH_SHORT).show();
        lv.setClickable(true);
        for(int i=0;i<attlist.length;i++)
        lv.setItemChecked(i,true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                String str = (String) o;//As you are using Default String Adapter
                CheckedTextView item = (CheckedTextView) arg1;
                Toast.makeText(FacAttendanceUpdateActivity.this, lv.getCount()+""+item.isChecked()+""+position + " "+str, Toast.LENGTH_SHORT).show();

            }
            });
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for(int i=0;i<lv.getCount();i++)
//                {
//                    if(lv.isItemChecked(i))
//                    {
//                        String b="true";
//                        //Toast.makeText(FacAttendanceUpdateActivity.this,lv.getAdapter().getItem(i)+"\t"+"ture", Toast.LENGTH_SHORT).show();
//                        hmap.put(lv.getAdapter().getItem(i).toString(),b);
//                    }
//                    else
//                    {
//                        String b="false";
//                        hmap.put(lv.getAdapter().getItem(i).toString(),b);
//                        //Toast.makeText(FacAttendanceUpdateActivity.this,lv.getAdapter().getItem(i)+"", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                ArrayList<Map.Entry<String, String>> arrayList = new ArrayList<>();
//                    Set set=hmap.entrySet();
//                Iterator i =set.iterator();
//                while(i.hasNext()) {
//                    Map.Entry mentry = (Map.Entry)i.next();
//                    if(mentry.getValue().equals("true")) {
//                        s.append(mentry.getKey());
//                        s.append(",");
//                        //Toast.makeText(FacAttendanceUpdateActivity.this, mentry.getKey() + "vlue=" + mentry.getValue(), Toast.LENGTH_SHORT).show();
//                    }
////                    else
////                    {
////                        Toast.makeText(FacAttendanceUpdateActivity.this, mentry.getKey() + "vlue=" + mentry.getValue(), Toast.LENGTH_SHORT).show();
////                    }
//                }
//                //arrayList.addAll(hmap.entrySet());
//                //Toast.makeText(FacAttendanceUpdateActivity.this,s, Toast.LENGTH_SHORT).show();
//                Toast.makeText(FacAttendanceUpdateActivity.this,"Updated Successfully", Toast.LENGTH_SHORT).show();
//                new addatt().execute();
//                update.setClickable(false);
//               }
//        });

        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(photo);
            //Bitmap newbm=Bitmap.createScaledBitmap(bm,(int)(bm.getWidth()*0.5), (int)(bm.getHeight()*0.5), true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            imgString = Base64.encodeToString(b, Base64.DEFAULT);
            URL url = null;
            try {
                url = new URL(RegURL.url + "Headcount");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            new SendPic().execute(url);
        }
    }
    private class SendPic extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = urls[0];
                JSONObject jsn = new JSONObject();
                jsn.put("img", imgString);
                String response = HttpClientConnection.executeClient(url, jsn);
                ires = response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ires;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(FacAttendanceUpdateActivity.this, ires, Toast.LENGTH_SHORT).show();
        }
    }
    public class addatt extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = new URL(RegURL.url + "UpdateAttendance");
                JSONObject jsn = new JSONObject();
                jsn.put("list", s);
                jsn.put("sec", section);
                jsn.put("scode", strsubj);
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
