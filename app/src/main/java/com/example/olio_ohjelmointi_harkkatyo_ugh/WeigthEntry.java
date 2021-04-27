package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class WeigthEntry extends AppCompatActivity {

    private Button weigth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        setContentView(R.layout.weigth_entry_view);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width*.7), (int) (heigth*.4));

        EditText weigth = (EditText) findViewById(R.id.enterWeigth);
    }

    public void enterWeigth(View v){
        float w = Float.parseFloat(weigth.getText().toString());
        //TODO save this float and datetime (and BMI?) to JSON and/or Array?



    }
}

