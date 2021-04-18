package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MealConstructor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_constructor);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void readJSON(View v) {
        String json = getJSON();
        System.out.println("JSON: " + json);

        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject jobject = jsonArray.getJSONObject(i);
                    System.out.println("#######"+ (i+1) +"########");
                    System.out.println(jobject.getString("Name"));
                    System.out.println(jobject.getDouble("energyKcal"));
                    System.out.println(jobject.getDouble("fat"));
                    System.out.println(jobject.getDouble("protein"));
                    System.out.println(jobject.getDouble("carbohydrate"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public String getJSON () {
        String response = null;
        try {
            URL url = new URL("https://fineli.fi/fineli/api/v1/foods/123");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            in.close();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
