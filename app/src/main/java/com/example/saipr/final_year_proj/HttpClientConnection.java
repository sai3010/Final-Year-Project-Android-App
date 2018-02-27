package com.example.saipr.final_year_proj;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Prateek on 1/24/2018.
 */

public class HttpClientConnection {

    public static String executeClient(URL url, JSONObject jsn) throws IOException, JSONException {

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        StringBuilder sbSend = new StringBuilder();

        Iterator<String> itr = jsn.keys();
        boolean first = true;

        while (itr.hasNext()){

            String key = itr.next();
            String value = jsn.get(key).toString();

            if(first){
                first = false;
            }

            else{
                sbSend.append("&");
            }

            sbSend.append(URLEncoder.encode(key, "UTF-8"));
            sbSend.append("=");
            sbSend.append(URLEncoder.encode(value, "UTF-8"));

        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        writer.write(sbSend.toString());
        writer.close();
        int responseCode = con.getResponseCode();

        if(responseCode == 200){

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder  sbRcv  = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null){
                sbRcv.append(line);
            }

            return sbRcv.toString();
        }

        else{
            return "Error " + responseCode + "! Data sending failed";
        }
    }

}
