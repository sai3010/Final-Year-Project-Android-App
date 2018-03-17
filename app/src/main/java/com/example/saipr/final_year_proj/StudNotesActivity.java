package com.example.saipr.final_year_proj;

import android.app.Dialog;
import android.app.ProgressDialog;
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

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class StudNotesActivity extends AppCompatActivity {
    private static final String TAG = StudNotesActivity.class.getSimpleName();
    String usn,sem;
    String response,str;
    LinearLayout lview;
    ListView lv;
    File file,folder;
    String br;
    String semnum;
    TextView semtitle;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_notes);
        ArrayList<String> fname=new ArrayList<>();
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        folder = new File(extStorageDirectory, "campusbridge");
        folder.mkdir();
        /*file = new File(folder, "android.pdf");
        try {
            if(!file.exists()) {
                file.createNewFile();
                Toast.makeText(this,"File===="+file.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/
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
        switch (usn.substring(5,7))
        {
            case "CS":br="cse";
                      break;
            case "EC":br="ece";
                break;
            case "EE":br="eee";
                break;
            case "CV":br="civil";
                break;
            case "IS":br="ise";
                break;
            case "ME":br="mech";
                break;
            case "EI":br="eie";
                break;
        }
        switch (sem)
        {
            case "1":semnum="sem1";
                break;
            case "2":semnum="sem2";
                break;
            case "3":semnum="sem3";
                break;
            case "4":semnum="sem4";
                break;
            case "5":semnum="sem5";
                break;
            case "6":semnum="sem6";
                break;
            case "7":semnum="sem7";
                break;
            case "8":semnum="sem8";
                break;
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lv.getItemAtPosition(position);
                str=(String)o;//As you are using Default String Adapter
                String url=RegURL.durl+"web/Notes/"+br+"/"+semnum;
                Toast.makeText(StudNotesActivity.this,url,Toast.LENGTH_SHORT).show();
                new DownloadFileFromURL().execute(url+"/"+str);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
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

    class DownloadFileFromURL extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }


        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream(folder+"/"+str);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    output.write(data, 0, count);
                }

                output.flush();

                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            /*// Displaying downloaded image into image view
            // Reading image path from sdcard
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.pdf";
            // setting downloaded into image view
            Toast.makeText(StudNotesActivity.this, ""+imagePath, Toast.LENGTH_SHORT).show();
//            my_image.setImageDrawable(Drawable.createFromPath(imagePath));*/
        }

    }

}
