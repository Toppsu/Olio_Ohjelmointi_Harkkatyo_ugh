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

        File path = getFilesDir();
        System.out.println(path);
        String userdir = path + "/" + DataHolder.getInstance().currentUser.getUsername() + "/Meals.json";
        System.out.println(userdir);
        String json = null;

        try {
            json = dbhelp.getJSON_wFileInputStreamer(userdir);
            System.out.println(json);
            String[] jsonsplit = json.split("(?<=])");
            for (String s : jsonsplit) {
                String singlemeal = "";
                JSONArray jarray = new JSONArray(s);
                JSONObject jobjectgetime = jarray.getJSONObject(0);
                String pvm = jobjectgetime.getString("pvm"); // In form "E dd-MM-yyyy HH:mm:ss" e.g "Tue 27-04-2021 14:41:15"
                singlemeal = singlemeal + "Meal: " + pvm + "\n\n";
                Double totKcal = 0.0;
                Double totCarbo = 0.0;
                Double totFat = 0.0;
                Double totProt = 0.0;
                for (int i=0; i<jarray.length(); i++) {

                    JSONObject jobject = jarray.getJSONObject(i);

                    int kerroin = (jobject.getInt("FoodAmount")/100);

                    totKcal = totKcal + jobject.getDouble("Kalorit")*kerroin;
                    totCarbo = totCarbo + jobject.getDouble("Carbohydrates")*kerroin;
                    totFat = totFat + jobject.getDouble("Protein")*kerroin;
                    totProt = totProt + jobject.getDouble("Fat")*kerroin;


                    String k ="Food: " + jobject.getString("Food") + "\t\t" + jobject.getInt("FoodAmount") + "g\n"
                            + "Kcal: " + Math.round(jobject.getDouble("Kalorit")*kerroin) + "\n"
                            + "Carbohydrates: " + Math.round(jobject.getDouble("Carbohydrates")*kerroin) + "g\t\t"
                            + "Protein: " + Math.round(jobject.getDouble("Protein")*kerroin) + "g\t\t"
                            + "Fat: " + Math.round(jobject.getDouble("Fat")*kerroin) + "g\n\n";
                    singlemeal = singlemeal + k;

                }
                String total = "\nTotal:\nKcal: " + Math.round(totKcal) + "\tCarbohydrates: "
                        + Math.round(totCarbo) + "g\tProtein: " + Math.round(totProt) +"g\tFat: " + Math.round(totFat) + "g\n";
                singlemeal = singlemeal + total;
                pastmeals.add(singlemeal);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,pastmeals);
        listView.setAdapter(arrayAdapter);
    }


}