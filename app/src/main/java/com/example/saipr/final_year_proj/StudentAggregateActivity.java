package com.example.saipr.final_year_proj;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentAggregateActivity extends AppCompatActivity {

    EditText s1 = null;
    EditText s2 = null;
    EditText s3 = null;
    EditText s4 = null;
    EditText s5 = null;
    EditText s6 = null;
    EditText s7 = null;
    EditText s8 = null;
    String usn;
    Double sum = 0.00;
    String res;
    int count = 0;
    String sse1, sse2, sse3, sse4, sse5, sse6, sse7, sse8;
    TextView calc = null;
    Button btnupdate=null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_student_aggregate);
            usn = getIntent().getExtras().getString("usn");

            s1 = findViewById(R.id.s1marks);
            s2 = findViewById(R.id.s2marks);
            s3 = findViewById(R.id.s3marks);
            s4 = findViewById(R.id.s4marks);
            s5 = findViewById(R.id.s5marks);
            s6 = findViewById(R.id.s6marks);
            s7 = findViewById(R.id.s7marks);
            s8 = findViewById(R.id.s8marks);
            calc = findViewById(R.id.calculate);
            btnupdate = findViewById(R.id.btnaggrupdate);

            new fetchAggregate().execute();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Toast.makeText(StudentAggregateActivity.this, res, Toast.LENGTH_LONG).show();

            String[] arr = res.split(",");

            s1.setText(arr[2]);
            s2.setText(arr[3]);
            s3.setText(arr[4]);
            s4.setText(arr[5]);
            s5.setText(arr[6]);
            s6.setText(arr[7]);
            s7.setText(arr[8]);
            s8.setText(arr[9]);
            for (int i = 2; i <= 9; i++)
            {
                if (!arr[i].equals("0.00"))
                {

                    count++;
                }

            }
            calc.setText(arr[10]);


            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sse1 = s1.getText().toString();
                    sse2 = s2.getText().toString();
                    sse3 = s3.getText().toString();
                    sse4 = s4.getText().toString();
                    sse5 = s5.getText().toString();
                    sse6 = s6.getText().toString();
                    sse7 = s7.getText().toString();
                    sse8 = s8.getText().toString();
                    count = 0;
                    if (!sse1.equals("0.00")) {
                        count++;
                    }if (!sse2.equals("0.00")) {
                        count++;
                    }if (!sse3.equals("0.00")) {
                        count++;
                    }if (!sse4.equals("0.00")) {
                        count++;
                    }if (!sse5.equals("0.00")) {
                        count++;
                    }if (!sse6.equals("0.00")) {
                        count++;
                    }if (!sse7.equals("0.00")) {
                        count++;
                    }if (!sse8.equals("0.00")) {
                        count++;
                    }
                    sum = (Double.parseDouble(sse1) + Double.parseDouble(sse2) + Double.parseDouble(sse3) + Double.parseDouble(sse4) + Double.parseDouble(sse5) + Double.parseDouble(sse6) + Double.parseDouble(sse7)
                            + Double.parseDouble(sse8)) / count;
                    String.format("%.5g%n", sum);
                    calc.setText(sum.toString());


                    try {
                        URL url = new URL(RegURL.url + "Receive_update");
                        JSONObject jsn = new JSONObject();
                        jsn.put("usn", usn);
                        jsn.put("sem1", sse1);
                        jsn.put("sem2", sse2);
                        jsn.put("sem3", sse3);
                        jsn.put("sem4", sse4);
                        jsn.put("sem5", sse5);
                        jsn.put("sem6", sse6);
                        jsn.put("sem7", sse7);
                        jsn.put("sem8", sse8);
                        jsn.put("agg", sum);
                        res = HttpClientConnection.executeClient(url, jsn);
                        Toast.makeText(StudentAggregateActivity.this, res, Toast.LENGTH_SHORT).show();
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });

        }

        public class fetchAggregate extends AsyncTask<URL, Void, String> {

            @Override
            protected String doInBackground(URL... urls) {

                try {
                    URL url = new URL(RegURL.url + "stud_aggregate");
                    JSONObject jsn = new JSONObject();
                    jsn.put("usn", usn);
                    res = HttpClientConnection.executeClient(url, jsn);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return res;
            }

        }
}
