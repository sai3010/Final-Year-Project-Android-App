package com.example.saipr.final_year_proj;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentDshboardActivity extends AppCompatActivity {
    private DrawerLayout menu;
    private ActionBarDrawerToggle mtoggle;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    ImageView ivAttachment;
    Button bUpload;
    TextView tvFileName;
    ProgressDialog dialog;
    int serverResponseCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dshboard);
        menu = (DrawerLayout) findViewById(R.id.drawer);
        mtoggle = new ActionBarDrawerToggle(this, menu, R.string.Open, R.string.Close);
        menu.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        verifyStoragePermissions(this);

        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        bUpload = (Button) findViewById(R.id.b_upload);
        tvFileName = (TextView) findViewById(R.id.tv_file_name);
        ivAttachment.setOnClickListener(this);
        bUpload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == ivAttachment) {

            //on attachment icon click
            showFileChooser();
        }
        if (v == bUpload) {

            //on upload button Click
            if (selectedFilePath != null) {
                dialog = ProgressDialog.show(StudentDshboardActivity.this, "", "Uploading File...", true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //creating new thread to handle Http Operations
                        uploadFile(selectedFilePath);
                    }
                }).start();
            } else {
                Toast.makeText(StudentDshboardActivity.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
            }

        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);
//                Toast.makeText(MainActivity.this, "selectedFilePath=" + selectedFilePath, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
                    tvFileName.setText(selectedFilePath);
                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void uploadFile(String sourceFileUri) {
        String serverResponseMessage = "";

        String upLoadServerUri = Settings.RegURL.upurl + "UploadData";
        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 10 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);
        if (!sourceFile.isFile()) {
            Log.e("uploadFile", "Source File Does not exist");
            // return 0;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(upLoadServerUri);
            conn = (HttpURLConnection) url.openConnection(); // Open a HTTP  connection to  the URL
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            //dos.writeUTF("Content-Disposition: form-data; name=\"lat\";lat=\""+ lat + "\"" + lineEnd);
            //  dos.writeUTF(lon);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);

            dos.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available(); // create a buffer of  maximum size

            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            serverResponseCode = conn.getResponseCode();
            serverResponseMessage = conn.getResponseMessage();


            //close the streams //
            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex) {

            ex.printStackTrace();
            Toast.makeText(getApplicationContext(), "MalformedURLException", Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }


        Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
        if (serverResponseCode == 200) {
            runOnUiThread(new Runnable() {
                public void run() {
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(), "File Upload Complete.", Toast.LENGTH_SHORT).show();
                }
            });
        }


        //   return serverResponseCode;
    }

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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

}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
