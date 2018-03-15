package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StudNotesActivity extends AppCompatActivity {
    private static final String TAG = StudNotesActivity.class.getSimpleName();
    String usn,sem;
    String response;
    LinearLayout lview;
    ListView lv;
    File file;
    TextView semtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_notes);
        ArrayList<String> fname=new ArrayList<>();
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "campusbridge");
        folder.mkdir();
        file = new File(folder, "android.pdf");
        try {
            if(!file.exists()) {
                file.createNewFile();
                Toast.makeText(this,"File===="+file.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        lview=findViewById(R.id.lview);
        semtitle=findViewById(R.id.title);
        lv=findViewById(R.id.lv);
        usn = getIntent().getExtras().getString("usn");
        sem = getIntent().getExtras().getString("sem");
        new MyNote().execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semtitle.setText("Sem"+" "+sem);
        String []f=response.split(",");
        Log.i(TAG, f[1]);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(StudNotesActivity.this,R.layout.activity_list_view,R.id.ltxtview,f);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        lv.setAdapter(adapter);
        //lview.addView(lv);
        //Toast.makeText(StudNotesActivity.this,response.toString(),Toast.LENGTH_SHORT).show();r
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                String url="C:/Users/saipr/Documents/NetBeansProjects/Final-Year-Project-Android-App/FinalYearProjectJSP/web/Notes/"+str;
                boolean downloadFile = downloadFile(url, file);
                /*if (file!=null && file.exists() && file.length() > 0){
                    Intent intent = new Intent(this, com.example.soniapdf.Second.class);
                    intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,
                            file.getAbsolutePath());
                    startActivity(intent);
                }*/
                if(downloadFile)
                Toast.makeText(getApplicationContext(), "yesss",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "nooo",Toast.LENGTH_SHORT).show();
            }
        });
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
    public  boolean downloadFile(String fileUrl, File directory) {
        try {
            FileOutputStream f = new FileOutputStream(directory);
            URL u = new URL(fileUrl);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            InputStream in = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len = 0;

            //
            int fileLength = c.getContentLength();
            long total = 0;
            //
            Toast.makeText(StudNotesActivity.this, "Downloading PDF...",Toast.LENGTH_SHORT).show();


            while ((len = in.read(buffer)) > 0) {
                total += len;


                //Toast.makeText(applicationContext, "Downloading PDF: remaining " + (fileLength / total  )+ "%", 1).show();
                f.write(buffer, 0, len);
            }
            f.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
