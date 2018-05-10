package com.example.saipr.final_year_proj;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class StudentDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = StudentDashboardActivity.class.getSimpleName();
    private String selectedFilePath = "";
    Bitmap bitmap = null;
    String name = "";
    String email = "";
    String usn = "";
    File file,folder;
    String lname,branch,address,phone,sem,password,url;
    TextView nametxt;
    TextView emailtxt;
    TextView usntxt;
    static ImageView imgv;
    CardView placement,markcard,attendance;
    Uri selectedFileUri;
    String imgString = "";
    String res = "";
    int lenghtOfFile;
    public String durl,surl;
    String extStorageDirectory;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    CardView notescard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        verifyStoragePermissions(this);
        extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        folder = new File(extStorageDirectory, "campusbridge");
        folder.mkdir();
        name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");
        usn = getIntent().getExtras().getString("usn");
        sem=getIntent().getExtras().getString("sem");
        branch=getIntent().getExtras().getString("branch");
        address=getIntent().getExtras().getString("address");
        phone=getIntent().getExtras().getString("phone");
        lname=getIntent().getExtras().getString("lname");
        password=getIntent().getExtras().getString("password");
        surl=folder+"/"+usn+".jpg";
        durl=RegURL.url+"Photos/"+"studprofile_photos/"+usn+".png";
        //new DownloadFileFromURL().execute(url);
       // Toast.makeText(StudentDashboardActivity.this,url,Toast.LENGTH_SHORT).show();
        /*placement card handling*/
        placement = findViewById(R.id.placement_card);
        placement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboardActivity.this, PlacementActivity.class);
                i.putExtra("usn", usn);
                startActivity(i);
            }
        });
        /*placement handling ends here*/
        /*marks*/
        markcard=findViewById(R.id.markcard);
        markcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboardActivity.this, StudMarksActivity.class);
                i.putExtra("usn", usn);
                i.putExtra("sem", sem);
                startActivity(i);
            }
        });
        /*marks end*/
        /*attendance*/
        attendance = findViewById(R.id.stattd);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentDashboardActivity.this,StudentAttendanceActivity.class);
                i.putExtra("usn", usn);
                i.putExtra("sem", sem);
                startActivity(i);
            }
        });
        /*Stud notes*/
        notescard=findViewById(R.id.notescard);
        notescard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentDashboardActivity.this,StudNotesActivity.class);
                i.putExtra("sem",sem);
                i.putExtra("usn",usn);
                startActivity(i);
            }
        });
        /*stud ends here*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private static long back_pressed;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (back_pressed + 2000 > System.currentTimeMillis()) {
                finish();
                System.exit(0);
            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_dashboard, menu);
        nametxt = findViewById(R.id.studname);
        emailtxt = findViewById(R.id.studemail);
        usntxt = findViewById(R.id.studusn);
        nametxt.setText(name);
        emailtxt.setText(email);
        usntxt.setText(usn);
      //  Toast.makeText(this, usn, Toast.LENGTH_SHORT).show();
        imgv = findViewById(R.id.imgbut);
        File f= new File(surl);
        if(f.exists())
        {
            Bitmap bm= resizeBitmap(surl, 200,100);
            imgv.setImageBitmap(bm);
        }else {
            //Toast.makeText(StudentDashboardActivity.this, "nulll", Toast.LENGTH_SHORT).show();
            imgv.setImageDrawable(getDrawable(R.drawable.student));
        }
        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentDashboardActivity.this, "Select an image to upload...", Toast.LENGTH_SHORT).show();
                showFileChooser();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.about)
        {
            Intent i = new Intent(StudentDashboardActivity.this,AboutStudActivity.class);
            i.putExtra("fname",name);
            i.putExtra("usn",usn);
            i.putExtra("lname",lname);
            i.putExtra("sem",sem);
            i.putExtra("branch",branch);
            i.putExtra("address",address);
            i.putExtra("phone",phone);
            i.putExtra("email",email);
            i.putExtra("password",password);
            startActivity(i);
        }
        else if (id == R.id.logout)
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
            startActivity(intent);
            finish();
        }
        else if(id==R.id.agg)
        {
            Intent intent1=new Intent(this, StudentAggregateActivity.class);
            intent1.putExtra("usn",usn);
            startActivity(intent1);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }
                selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);


                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedFileUri);
                    Bitmap bm= resizeBitmap(selectedFilePath, 200,100);
                    imgv.setImageBitmap(bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }

               // Toast.makeText(StudentDashboardActivity.this, "selectedFilePath=" + selectedFilePath, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {

                    if (selectedFilePath != null) {
                        uploadFile(selectedFilePath);
                        File f1=getTempFile(this, selectedFilePath);
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedFileUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgv.setImageBitmap(bitmap);
                        //Toast.makeText(this, f1.toString(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(StudentDashboardActivity.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File getTempFile(Context context, String url) {
        File file=null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }

    private class SendPic extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                URL url = urls[0];
                JSONObject jsn = new JSONObject();
                jsn.put("img", imgString);
                jsn.put("usn", usn);
                String response = HttpClientConnection.executeClient(url, jsn);
                res = response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // Toast.makeText(StudentDashboardActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadFile(String sourceFileUri) {

        if (imgv.getDrawable() != null) {
            Bitmap bm = ((BitmapDrawable) imgv.getDrawable()).getBitmap();
            Bitmap newbm=Bitmap.createScaledBitmap(bm,(int)(bm.getWidth()*0.5), (int)(bm.getHeight()*0.5), true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            newbm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            imgString = Base64.encodeToString(b, Base64.DEFAULT);
            try {
                URL url = new URL(RegURL.url + "UploadData");
                new SendPic().execute(url);
                Thread.sleep(2000);
                if(res.equals("ok"))
                {
                    new DownloadFileFromURL().execute(durl);
                }else
                    Toast.makeText(this, "cannot download", Toast.LENGTH_SHORT).show();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    class DownloadFileFromURL extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //showDialog(progress_bar_type);
        }


        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                lenghtOfFile = conection.getContentLength();
                //Log.i(TAG, "length:::"+ lenghtOfFile+"");
                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

               // Log.i(TAG, "inputtt:::"+ input.toString());
                // Output stream
                OutputStream output = new FileOutputStream(folder+"/"+usn+".jpg");
                //Log.i(TAG, "ouputtt:::"+ output.toString());
                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    output.write(data, 0, count);

                }
                //Log.i(TAG, "IIIIIIIIIIIIIIIIIIIIIIIIIIIIII--- "+count+"\t"+data);

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
            //pDialog.setProgress(Integer.parseInt(progress[0]));
        }
        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            //dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            String imagePath = Environment.getExternalStorageDirectory().toString() +"/campusbridge"+"/"+usn+".jpg";
            // setting downloaded into image view
           // Toast.makeText(StudentDashboardActivity.this, ""+imagePath, Toast.LENGTH_SHORT).show();
//            File directory=new File(imagePath);
//            File[] flist=directory.listFiles();
//            for (File file : flist) {
//                if (file.isFile()) {
//                    //System.out.println(file.getName());
//                    Toast.makeText(StudentDashboardActivity.this, file.getName().toString(), Toast.LENGTH_SHORT).show();
//                    //url.add(fileDir+file.getName());
//                }
//            }
            File f= new File(imagePath);
            if(!f.exists())
            {
                //Toast.makeText(StudentDashboardActivity.this, "nulll", Toast.LENGTH_SHORT).show();
                imgv.setImageDrawable(getDrawable(R.drawable.defaultpic));
            }else {
                Bitmap bm= resizeBitmap(imagePath, 170,100);

                imgv.setImageBitmap(bm);
            }
        }

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
