package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class List_past_meals extends AppCompatActivity {

    ArrayAdapter arrayAdapter;
    ArrayList<String> pastmeals = new ArrayList<>();
    String[] pastmealnames;

    Context context;

    DatabaseHelper dbhelp = new DatabaseHelper(List_past_meals.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_past_meals);

        ListView listView = (ListView) findViewById(R.id.MealHistoryListView);

        File dir = getFilesDir();
        pastmealnames = dir.list();

        for (String pastmealname : pastmealnames) {
            System.out.println(pastmealname);
            String json = null;

            try {

                json = dbhelp.getJSON(pastmealname);
                System.out.println(json);
                String[] jsonsplit = json.split("(?<=])");
                for (String s : jsonsplit) {
                    String yksittainenruokailu = "";
                    JSONArray jarray = new JSONArray(s);
                    JSONObject jobjectgetime = jarray.getJSONObject(0);
                    String pvm = jobjectgetime.getString("pvm"); // In form "E dd-MM-yyyy HH:mm:ss" e.g "Tue 27-04-2021 14:41:15"
                    yksittainenruokailu = yksittainenruokailu + "Ruokailu: " + pvm + "\n\n";
                    Double totKcal = 0.0;
                    Double totHiilari = 0.0;
                    Double totRasva = 0.0;
                    Double totProt = 0.0;
                    for (int i=0; i<jarray.length(); i++) {

                        JSONObject jobject = jarray.getJSONObject(i);

                        int kerroin = (jobject.getInt("Ruokamaara")/100);

                        totKcal = totKcal + jobject.getDouble("Kalorit")*kerroin;
                        totHiilari = totHiilari + jobject.getDouble("Hiilarit")*kerroin;
                        totRasva = totRasva + jobject.getDouble("Proteiini")*kerroin;
                        totProt = totProt + jobject.getDouble("Rasva")*kerroin;


                        String k ="Ruoka: " + jobject.getString("Ruoka") + "\t\t" + jobject.getInt("Ruokamaara") + "g\n"
                                + "Kcal: " + Math.round(jobject.getDouble("Kalorit")*kerroin) + "\n"
                                + "Hiilarit: " + Math.round(jobject.getDouble("Hiilarit")*kerroin) + "g\t\t"
                                + "Proteiini: " + Math.round(jobject.getDouble("Proteiini")*kerroin) + "g\t\t"
                                + "Rasva: " + Math.round(jobject.getDouble("Rasva")*kerroin) + "g\n\n";
                        yksittainenruokailu = yksittainenruokailu + k;

                    }
                    String total = "\nYhteensä:\nKcal: " + Math.round(totKcal) + "\tHiilarit: "
                            + Math.round(totHiilari) + "g\tRasva: " + Math.round(totRasva)
                            + "g\tProteiini: " + Math.round(totProt) + "g";
                    yksittainenruokailu = yksittainenruokailu + total;
                    pastmeals.add(yksittainenruokailu);

                }



            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,pastmeals);
        listView.setAdapter(arrayAdapter);
    }


}