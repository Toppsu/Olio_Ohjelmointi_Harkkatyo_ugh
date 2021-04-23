package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenView extends AppCompatActivity {

    private Button workoutButton;
    private Button mealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_view);

        workoutButton = (Button) findViewById(R.id.reeniNappula);
        mealButton = (Button) findViewById(R.id.mealButton);

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutActivity();
            }
        });

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMealActivity();
            }
        });

    }

    private void openMealActivity() {
        Intent intent = new Intent(this,MealActivity.class);
        startActivity(intent);
    }

    private void openWorkoutActivity() {
        Intent intent = new Intent(this,WorkoutActivity.class);
        startActivity(intent);
    }


}