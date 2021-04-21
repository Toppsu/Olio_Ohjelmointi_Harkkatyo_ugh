package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public String valittu_ruoka = "pekoni";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_constructor);
        /* Setting strict mode*/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /* Activity_meal_mainscreenistä tuleva ja sinne menevä ruokanimi */
        int ruoan_nappi_id = getIntent().getIntExtra("ruoka_tavara", 420);
        System.out.println("Valittu ruoka printti = " + valittu_ruoka);


        /* Finding listView and EditText*/
        TextView textView = (TextView) findViewById(R.id.Ruoka1);
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
            textView.setText(ruokalista.get(position));
            valittu_ruoka = ruokalista.get(position);
                System.out.println("Valittu ruoka uudestaan (valinta valittu) " + valittu_ruoka);

            }
        });


    }


        /* Read the JSON and outputs nutritional values*/
    public void readJSON(View v) {
        int id = 123;
        /*String json = FineliAPI.getJSON(id);*/
        /*System.out.println("JSON: " + json);*/
        ArrayList<String> ruokainfo = new ArrayList<>(FineliAPI.readJSON(id));

        for (int i = 0; i < ruokainfo.size();i++)
        {
            System.out.println(ruokainfo.get(i));
        }
    }

    public void valmis_valinta(View v) {

        Intent intent = new Intent();
        intent.putExtra("123", valittu_ruoka);
        setResult(RESULT_OK, intent);
        finish();
    }

}
