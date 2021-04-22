package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ArrayList<String> ruokalista = new ArrayList<>();

    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_constructor);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /* Finding listView and EditText*/
        TextView TextView = (TextView) findViewById(R.id.Ruoka1);
        ListView listView = (ListView) findViewById(R.id.listview);
        EditText ruokafilter = (EditText) findViewById(R.id.searchFilter);

        /* Opening inputstream from raw folder to get foodnames (File is from https://fineli.fi/fineli/content/file/47)*/
        InputStream inputStream = getResources().openRawResource(R.raw.foodnames);
        Scanner scanner = new Scanner(inputStream);

        /* Appending foodnames.txt content to ruokalista arraylist*/
        while(scanner.hasNextLine()) {
            ruokalista.add(scanner.nextLine()/*.split(",")[1]*/);
        }
        scanner.close();

        /*Checking size*/
        System.out.println(ruokalista.size());

        /* Creating adapter for the listview*/
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ruokalista);
        listView.setAdapter(arrayAdapter);


        ruokafilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (MealConstructor.this).arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView.setText(ruokalista.get(position));

            }
        });


    }
        /* Read the JSON and outputs nutritional values*/
    public void readJSON(View v) {
        int id = 123;
        String json = getJSON(id);
        System.out.println("JSON: " + json);

        if (json != null) {
            try {
                JSONObject jobject = new JSONObject(json);
                JSONObject jobjectRuokanimi = new JSONObject(jobject.getString("name"));
                System.out.println("####### RUOKAINFO ########");
                System.out.println(jobject.getString("unit"));
                System.out.println(jobjectRuokanimi.getString("fi"));
                System.out.println(jobject.getDouble("energyKcal"));
                System.out.println(jobject.getDouble("fat"));
                System.out.println(jobject.getDouble("protein"));
                System.out.println(jobject.getDouble("carbohydrate"));

                for(int i = 0; i < ruokalista.size(); i++) {
                    System.out.print(ruokalista.get(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
    /* Fethces the JSON from fineli API*/
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
