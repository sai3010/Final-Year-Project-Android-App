package com.example.saipr.final_year_proj;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

public class FacultyDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = FacultyDashboardActivity.class.getSimpleName();
    private String selectedFilePath="";
    ProgressDialog dialog;
    int serverResponseCode = 0;
    String name = "";
    String email = "";
   static String usn = "";
    String lname,branch,address,phone,qual,password;
    TextView nametxt;
    TextView emailtxt;
    TextView usntxt;
    CardView facnotes,markscard;
    Intent intent;
   static ImageView imgv;
    Uri selectedFileUri;
    String imgString = "";
    String res = "";
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_dashboard);
        verifyStoragePermissions(this);
        /*getting usn through intent*/
        name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");
        usn = getIntent().getExtras().getString("usn");
        qual=getIntent().getExtras().getString("qual");
        branch=getIntent().getExtras().getString("branch");
        address=getIntent().getExtras().getString("address");
        phone=getIntent().getExtras().getString("phone");
        lname=getIntent().getExtras().getString("lname");
        password=getIntent().getExtras().getString("password");

        /*marks card click*/
        markscard=findViewById(R.id.markscard);
        markscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(FacultyDashboardActivity.this,FacMarksActivity.class);
                intent.putExtra("usn",usn);
                startActivity(intent);
            }
        });

        /*fac notes navigation*/
        facnotes=findViewById(R.id.facnotes);
        facnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(FacultyDashboardActivity.this,FacNotesActivity.class);
                intent.putExtra("usn",usn);
                startActivity(intent);
            }
        });

        /*Image setting*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        getMenuInflater().inflate(R.menu.faculty_dashboard, menu);
        nametxt=findViewById(R.id.facname);
        emailtxt=findViewById(R.id.facemail);
        usntxt=findViewById(R.id.facusn);
        nametxt.setText(name);
        emailtxt.setText(email);
        usntxt.setText(usn);
        Toast.makeText(this, usn, Toast.LENGTH_SHORT).show();
        imgv= findViewById(R.id.imgbut);

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FacultyDashboardActivity.this, "Select an image to upload...", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(FacultyDashboardActivity.this,AboutFacActivity.class);
            i.putExtra("fname",name);
            i.putExtra("lname",lname);
            i.putExtra("qual",qual);
            i.putExtra("branch",branch);
            i.putExtra("address",address);
            i.putExtra("phone",phone);
            i.putExtra("email",email);
            i.putExtra("password",password);
            i.putExtra("usn",usn);
            startActivity(i);
        }
        else if (id == R.id.logout)
        {
            dialog = ProgressDialog.show(FacultyDashboardActivity.this, "", "Logging Out...",true);
            Intent intent = new Intent(FacultyDashboardActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
            startActivity(intent);
            finish();
        }
        else if (id == R.id.push)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedFileUri);
                    imgv.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(FacultyDashboardActivity.this, "selectedFilePath=" + selectedFilePath, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {


                    if (selectedFilePath != null) {
                        uploadFile(selectedFilePath);
                    } else {
                        Toast.makeText(FacultyDashboardActivity.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
            Toast.makeText(FacultyDashboardActivity.this, res, Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadFile(String sourceFileUri) {

        if (imgv.getDrawable() != null) {
            Bitmap bm = ((BitmapDrawable) imgv.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            imgString = Base64.encodeToString(b, Base64.DEFAULT);
            try {
                URL url = new URL(RegURL.url + "UploadData");
                new SendPic().execute(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
