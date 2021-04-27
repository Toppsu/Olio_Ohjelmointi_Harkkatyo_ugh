package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenView extends AppCompatActivity {

    private Button strengthWorkoutButton;
    private Button cardioWorkoutButton;
    private Button mealButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_view);

        strengthWorkoutButton = (Button) findViewById(R.id.strengthWorkoutButton);
        cardioWorkoutButton = (Button) findViewById(R.id.cardioWorkoutButton);
        mealButton = (Button) findViewById(R.id.mealButton);
    }

    public void openMealActivity(View v){
        Intent intent = new Intent(this, Meal_mainscreen.class);
        startActivity(intent);
    }

    private void openWorkoutActivity(View v) {
        Intent intent = new Intent(this,WorkoutActivity.class);
        startActivity(intent);
    }

    public void openMealHistory(View v) {
        Intent intent = new Intent(this,List_past_meals.class);
        startActivity(intent);
    }


}