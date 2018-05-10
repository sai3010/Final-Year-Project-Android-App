package com.example.saipr.final_year_proj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AboutStudActivity extends AppCompatActivity {
    String fname = "";
    String email = "";
    String usn = "";
    String lname,branch,address,phone,sem,password;
    EditText fnameet,lnameet,emailet,phoneet,addresset,passet;
    Button updatebtn;
    URL url;
    ImageView profpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_stud);
        fnameet=findViewById(R.id.fnameet);
        lnameet=findViewById(R.id.lnameet);
        emailet=findViewById(R.id.emailet);
        phoneet=findViewById(R.id.phoneet);
        addresset=findViewById(R.id.addresset);
        passet=findViewById(R.id.passet);
        updatebtn=findViewById(R.id.updatebtn);
        profpic=findViewById(R.id.sprofile_image);
        fname = getIntent().getExtras().getString("fname");
        email = getIntent().getExtras().getString("email");
        branch=getIntent().getExtras().getString("branch");
        address=getIntent().getExtras().getString("address");
        phone=getIntent().getExtras().getString("phone");
        lname=getIntent().getExtras().getString("lname");
        password=getIntent().getExtras().getString("password");
        usn=getIntent().getExtras().getString("usn");
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        String purl=extStorageDirectory+"/campusbridge/"+usn+".jpg";
        File f= new File(purl);
        if(!f.exists())
        {
            profpic.setImageDrawable(getDrawable(R.drawable.defaultpic));
        }else
        {
            Bitmap bm= resizeBitmap(purl,200,100);
            profpic.setImageBitmap(bm);
        }
        Toast.makeText(this, purl, Toast.LENGTH_SHORT).show();
        fnameet.setText(fname);
        lnameet.setText(lname);
        emailet.setText(email);
        phoneet.setText(phone);
        addresset.setText(address);
        passet.setText(password);




        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    url = new URL(RegURL.url + "Aboutstud");
                    JSONObject jsn = new JSONObject();
                    jsn.put("fname",fnameet.getText().toString());
                    jsn.put("lname",lnameet.getText().toString());
                    jsn.put("email",emailet.getText().toString());
                    jsn.put("phone",phoneet.getText().toString());
                    jsn.put("address",addresset.getText().toString());
                    jsn.put("password",passet.getText().toString());
                    jsn.put("usn",usn);

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(policy);
                    String response = HttpConnection.getResponse(url,jsn);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public Bitmap resizeBitmap(String photoPath, int targetW, int targetH) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true; //Deprecated API 21

        return BitmapFactory.decodeFile(photoPath, bmOptions);
    }
}
