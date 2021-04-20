package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MealConstructor extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_constructor);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView = (ListView) findViewById(R.id.listview);


 /*  KOMMENTOITU POIS TESTAAMISTA VARTEN -Elmeri

        InputStream inputStream = getResources().openRawResource(R.raw.foodnames);

        Scanner scanner = new Scanner(inputStream);
        ArrayList<String> ruokalista = new ArrayList<>();
        while(scanner.hasNextLine()) {
            ruokalista.add(scanner.nextLine()/*.split(",")[1]);
        }
        scanner.close();

        System.out.println(ruokalista.size());

        for(int i = 0; i < ruokalista.size(); i++) {
            System.out.print(ruokalista.get(i));
        }
*/
    }

    public void readJSON(View v) {
        int id = 123;
        String json = getJSON(id);
        System.out.println("JSON: " + json);

        if (json != null) {
            try {
                JSONObject jobject = new JSONObject(json);
                System.out.println("####### RUOKAINFO ########");
                System.out.println(jobject.getString("unit"));
                System.out.println(jobject.getDouble("energyKcal"));
                System.out.println(jobject.getDouble("fat"));
                System.out.println(jobject.getDouble("protein"));
                System.out.println(jobject.getDouble("carbohydrate"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public String getJSON (int id) {
        String response = null;
        try {
            URL url = new URL("https://fineli.fi/fineli/api/v1/foods/" + id);
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
