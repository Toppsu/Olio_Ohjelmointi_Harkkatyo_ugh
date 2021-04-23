package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Meal_mainscreen extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

    private String ruokanumero1 = "raaka_pekoni";
    private String ruokanumero2 = "raaka_pekoni";
    private String ruokanumero3 = "raaka_pekoni";

    TextView textView1;
    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_mainscreen);

    }

    /* Kun lisätään ruoka, mennään mealConstructoriin */
    public void Button1press (View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int nappiID = 1;
        intent.putExtra("ruoka_tavara", nappiID);
        startActivityForResult(intent, 123);
    }

    public void Button2press (View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int nappiID = 2;
        intent.putExtra("ruoka_tavara", nappiID);
        startActivityForResult(intent, 123456);
    }

    public void Button3press (View v) {
        Intent intent = new Intent(this, MealConstructor.class);
        int nappiID = 3;
        intent.putExtra("ruoka_tavara", nappiID);
        startActivityForResult(intent, 123456789);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {

                ruokanumero1 = (String) data.getSerializableExtra("123");
                textView1 = (TextView) findViewById(R.id.food1text);
                textView1.setText(ruokanumero1);
            }
        }
        if (requestCode == 123456) {
            if (resultCode == RESULT_OK) {
                ruokanumero2 = (String) data.getSerializableExtra("123456");
                textView2.setText(ruokanumero2);
                textView2 = (TextView) findViewById(R.id.food2text);
            }
        }
        if (requestCode == 123456789) {
            if (resultCode == RESULT_OK) {
                ruokanumero3 = (String) data.getSerializableExtra("123456789");
                textView3 = (TextView) findViewById(R.id.food3text);
                textView3.setText(ruokanumero3);

            }
        }
    }
}
