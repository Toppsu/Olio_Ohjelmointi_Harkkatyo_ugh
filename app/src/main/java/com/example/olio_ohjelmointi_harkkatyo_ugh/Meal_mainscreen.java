package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Calendar;

public class Meal_mainscreen extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

    private String ruokanumero1 = null;
    private String ruokanumero2 = null;
    private String ruokanumero3 = null;

    TextView textView1;
    TextView textView2;
    TextView textView3;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_mainscreen);
        context = Meal_mainscreen.this;

        EditText ruokamaara1_yksikko = (EditText) findViewById(R.id.ruoka2maara);
        EditText ruokamaara2_yksikko = (EditText) findViewById(R.id.ruoka2maara);
        EditText ruokamaara3_yksikko = (EditText) findViewById(R.id.ruoka3maara);
    }

    /* Kun lisätään ruoka, mennään mealConstructoriin */
    public void Button1press(View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int nappiID = 1;
        intent.putExtra("ruoka_tavara", nappiID);
        startActivityForResult(intent, 1);
    }

    public void Button2press(View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int nappiID = 2;
        intent.putExtra("ruoka_tavara", nappiID);
        startActivityForResult(intent, 12);
    }

    public void Button3press(View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int nappiID = 3;
        intent.putExtra("ruoka_tavara", nappiID);
        startActivityForResult(intent, 123);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                ruokanumero1 = (String) data.getSerializableExtra("1");
                String[] ruokanumero1_name = ruokanumero1.split("\\t");
                textView1 = (TextView) findViewById(R.id.food1text);
                textView1.setText(ruokanumero1_name[1]);

                /*EditText ruokamaara1_yksikko = (EditText) findViewById(R.id.ruoka1maara);
                ruokamaara1_yksikko.setText(FineliAPI.readJSON(Integer.parseInt(ruokanumero1_name[0])).get(5).toString());*/

            }
        }
        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                ruokanumero2 = (String) data.getSerializableExtra("12");
                String[] ruokanumero2_name = ruokanumero2.split("\\t");
                textView2 = (TextView) findViewById(R.id.food2text);
                textView2.setText(ruokanumero2_name[1]);
            }
        }
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                ruokanumero3 = (String) data.getSerializableExtra("123");
                String[] ruokanumero3_name = ruokanumero3.split("\\t");
                textView3 = (TextView) findViewById(R.id.food3text);
                textView3.setText(ruokanumero3_name[1]);

            }
        }
    }

    public void MealActivityReady(View v) {

        ArrayList<Object> ruokailu = new ArrayList<>();

        if (ruokanumero1 != null) {
            String[] parts = ruokanumero1.split("\\t");
            String ruoka1_id = parts[0];
            String ruoka1_nimi = parts[1];
            EditText ruoka1grammat = (EditText) findViewById(R.id.ruoka1maara);
            int grammat = Integer.parseInt(ruoka1grammat.getText().toString());
            ArrayList<String> ruokanumero1arraylist = FineliAPI.readJSON(Integer.parseInt(ruoka1_id));
            for (int i = 0; i < ruokanumero1arraylist.size(); i++) {
                System.out.println(ruokanumero1arraylist.get(i));
            }
            MealActivity ruokanumero1_olio
                    = new MealActivity(Float.parseFloat(ruokanumero1arraylist.get(0))
                    , Float.parseFloat(ruokanumero1arraylist.get(1))
                    , Float.parseFloat(ruokanumero1arraylist.get(2))
                    , Float.parseFloat(ruokanumero1arraylist.get(3))
                    , ruoka1_nimi
                    ,grammat);
            ruokailu.add(ruokanumero1_olio);
        }
        if (ruokanumero2 != null) {
            String[] parts = ruokanumero2.split("\\t");
            String ruoka2_id = parts[0];
            String ruoka2_nimi = parts[1];
            EditText ruoka2grammat = (EditText) findViewById(R.id.ruoka2maara);
            int grammat = Integer.parseInt(ruoka2grammat.getText().toString());
            ArrayList<String> ruokanumero2arraylist = FineliAPI.readJSON(Integer.parseInt(ruoka2_id));
            for (int i = 0; i < ruokanumero2arraylist.size(); i++) {
                System.out.println(ruokanumero2arraylist.get(i));
            }
            MealActivity ruokanumero2_olio
                    = new MealActivity(Float.parseFloat(ruokanumero2arraylist.get(0))
                    , Float.parseFloat(ruokanumero2arraylist.get(1))
                    , Float.parseFloat(ruokanumero2arraylist.get(2))
                    , Float.parseFloat(ruokanumero2arraylist.get(3))
                    , ruoka2_nimi
                    ,grammat);
            ruokailu.add(ruokanumero2_olio);
        }
        if (ruokanumero3 != null) {
            String[] parts = ruokanumero3.split("\\t");
            String ruoka3_id = parts[0];
            String ruoka3_nimi = parts[1];
            EditText ruoka3grammat = (EditText) findViewById(R.id.ruoka3maara);
            int grammat = Integer.parseInt(ruoka3grammat.getText().toString()); //TODO MAKE ERROR IF WEIGHT EMPTY
            ArrayList<String> ruokanumero3arraylist = FineliAPI.readJSON(Integer.parseInt(ruoka3_id));
            for (int i = 0; i < ruokanumero3arraylist.size(); i++) {
                System.out.println(ruokanumero3arraylist.get(i));
            }
            MealActivity ruokanumero3_olio
                    = new MealActivity(Float.parseFloat(ruokanumero3arraylist.get(0))
                    , Float.parseFloat(ruokanumero3arraylist.get(1))
                    , Float.parseFloat(ruokanumero3arraylist.get(2))
                    , Float.parseFloat(ruokanumero3arraylist.get(3))
                    , ruoka3_nimi
                    ,grammat);
            ruokailu.add(ruokanumero3_olio);
        }
        TallennaRuokailu(ruokailu);
    }

    public String GetRuokailuNimi () {
        EditText ruokailunnimi = (EditText) findViewById(R.id.RuokailunNimi);
        String nimi = ruokailunnimi.getText().toString();
        return nimi;
    }

    public void TallennaRuokailu(ArrayList ruokailu) {
        if (ruokailu != null) {
            String nimi = GetRuokailuNimi();
            String filename = nimi + " " + Calendar.getInstance().getTime() + ".json";
            System.out.println(filename);
            Gson gson = new Gson();
            String s = gson.toJson(ruokailu);
            System.out.println(context.getFilesDir());
            try {
                OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE)); //TODO Korjaas tästä tää, antaa null pointer expressionin
                ows.write(s);
                ows.close();
            } catch (FileNotFoundException e) {
                Log.e("FileNotFound", String.valueOf(R.string.FileNotFound));

            } catch (IOException e) {
                Log.e("IOException", String.valueOf(R.string.IOException));
            }
        }
    }
}

        /*ArrayList testi = FineliAPI.readJSON(123);
        paivamaara
        Ruokalista
        Kalorit
        Rasva
        Hiilarit
        Proteiini
        MealActivity mealactivity = new MealActivity()
        for (int i = 0; i < testi.size(); i++ ) {
            System.out.println(testi.get(i));
            */


