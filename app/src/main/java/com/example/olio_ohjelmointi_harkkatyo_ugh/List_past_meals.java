package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
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
/* We fetch the meal array with mealhistory*/
    MealHistory mealhistory = new MealHistory();
    /* Basic lisview setup*/
    ArrayAdapter arrayAdapter;
    ArrayList<String> pastmeals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_past_meals);
        pastmeals = DataHolder.getInstance().MealHistoryArray;
        ListView listView = (ListView) findViewById(R.id.MealHistoryListView);
        /* So pastmeals contains all the past meals in the right string form for the arrayadapter*/
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,pastmeals);
        listView.setAdapter(arrayAdapter);
    }

    public void back(View v) {
        Intent intent = new Intent(this,MainScreenView.class);
        startActivity(intent);
    }

}