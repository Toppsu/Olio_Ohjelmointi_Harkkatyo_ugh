package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreenView extends AppCompatActivity {

    private Button workoutButton;
    private Button mealButton;
    private Button userInfo;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_view);

        context = MainScreenView.this;

        workoutButton = (Button) findViewById(R.id.workoutButton);
        mealButton = (Button) findViewById(R.id.mealButton);
        userInfo = (Button) findViewById(R.id.userInfoButton);
        Button weigthEntry = (Button) findViewById(R.id.addWeigth);

        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkoutActivity(v);
            }
        });
    }



    public void openUserInfoActivity(View v) {
        Intent intent = new Intent(this,UserInfo.class);
        startActivity(intent);
    }

    public void openMealActivity(View v) {
        Intent intent = new Intent(this,Meal_mainscreen.class);
        startActivity(intent);
    }

    public void openWorkoutActivity(View v) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void openMealHistory(View v) {
        Intent intent = new Intent(this,List_past_meals.class);
        startActivity(intent);
    }

    public void openWeigthEntry(View v) {
        Intent intent = new Intent(this,WeigthEntry.class);
        startActivity(intent);
    }



}