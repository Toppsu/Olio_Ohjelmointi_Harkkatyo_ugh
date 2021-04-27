package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MealConstructor extends AppCompatActivity {
    ArrayList<String> FoodListFin = new ArrayList<>();
    ArrayList<String> FoodListEN = new ArrayList<>();


    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapterEN;

    public String chosen_food = "bacon";
    public int food_button_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_constructor);
        /* Setting strict mode*/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /* To which foodslot the fooditem is going */
        food_button_id = getIntent().getIntExtra("food_item", 420);
        System.out.println("Valittu ruoka printti = " + chosen_food);


        /* Finding listView and EditText*/
        TextView textView = (TextView) findViewById(R.id.Ruoka1);
        ListView listView = (ListView) findViewById(R.id.listview);
        EditText foodfilter = (EditText) findViewById(R.id.searchFilter);


        /* Opening inputstream from raw folder to get finnish foodnames (File is from https://fineli.fi/fineli/content/file/47)*/
        InputStream inputStream = getResources().openRawResource(R.raw.foodnames); //TODO DOES NOT PICK RIGHT ITEM IF SEARCHED
        Scanner scanner = new Scanner(inputStream);

        /* Appending foodnames.txt content to ruokalista arraylist*/
        while(scanner.hasNextLine()) {
            FoodListFin.add(scanner.nextLine()/*.split(",")[1]*/);
        }
        scanner.close();


        /* Same but for english foodnames*/
        InputStream inputStream2 = getResources().openRawResource(R.raw.foodnames_en); //TODO DOES NOT PICK RIGHT ITEM IF SEARCHED
        Scanner scanner2 = new Scanner(inputStream2);
        while(scanner2.hasNextLine()) {
            FoodListEN.add(scanner2.nextLine());
        }
        scanner2.close();


        /*Checking size*/
        System.out.println(FoodListEN.size());


        /* Creating adapters for the listviews*/
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, FoodListFin);
        arrayAdapterEN = new ArrayAdapter(this, android.R.layout.simple_list_item_1, FoodListEN);

        listView.setAdapter(arrayAdapterEN);

        foodfilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (MealConstructor.this).arrayAdapterEN.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String[] onitemclickruokanimi = FoodListEN.get(position).split("\\t");
            String textviewruokaid = onitemclickruokanimi[0];
            String textviewruokanimi = onitemclickruokanimi[1];
            textView.setText(textviewruokanimi);
            chosen_food = FoodListEN.get(position);
                System.out.println("Valittu ruoka uudestaan (valinta valittu) " + chosen_food);

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

    if (food_button_id == 1) {
        Intent intent = new Intent();
        intent.putExtra("1", chosen_food);
        setResult(RESULT_OK, intent);
        finish();
        }

    if (food_button_id == 2) {
        Intent intent = new Intent();
        intent.putExtra("12", chosen_food);
        setResult(RESULT_OK, intent);
        finish();
    }

    if (food_button_id == 3) {
        Intent intent = new Intent();
        intent.putExtra("123", chosen_food);
        setResult(RESULT_OK, intent);
        finish();
        }
    }

}
