package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin = findViewById(R.id.signButton);
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegisterActivity();
            }
        });
    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openMealActivity(View v){
        Intent intent = new Intent(this, MealConstructor.class);
        startActivity(intent);
    }
}