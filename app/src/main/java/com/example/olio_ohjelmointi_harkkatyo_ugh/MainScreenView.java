package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreenView extends AppCompatActivity {

    private Button strengthWorkoutButton;
    private Button cardioWorkoutButton;
    private Button mealButton;
    private Button userInfo;
    Context context = null;
    TextView bmi;
    DataHolder dh = DataHolder.getInstance();
    Double BMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_view);

        context = MainScreenView.this;

        strengthWorkoutButton = (Button) findViewById(R.id.strengthWorkoutButton);
        cardioWorkoutButton = (Button) findViewById(R.id.cardioWorkoutButton);
        mealButton = (Button) findViewById(R.id.mealButton);
        userInfo = (Button) findViewById(R.id.userInfoButton);

        bmi = (TextView) findViewById(R.id.bmi);

        double h = (double)dh.currentUser.getHeigth() / (double)100;
        int w = dh.currentUser.getWeigth();

        System.out.println(w+";"+h);

        if(h != 0 & w != 0) {
            BMI = w / Math.pow(h, 2);
            bmi.setText("BMI: "+ String.format("%.2f", BMI));
        } else {
            bmi.setText("BMI: N/A");
        }


    }

    public void openUserInfoActivity(View v) {
        Intent intent = new Intent(this,UserInfo.class);
        startActivity(intent);
    }

    public void openMealActivity(View v) {
        Intent intent = new Intent(this,Meal_mainscreen.class);
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