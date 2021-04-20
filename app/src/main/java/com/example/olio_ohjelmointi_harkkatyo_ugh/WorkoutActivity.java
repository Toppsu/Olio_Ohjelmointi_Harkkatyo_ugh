package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class WorkoutActivity extends AppCompatActivity {

    private RecyclerView workoutRecyclerView;
    private RecyclerView.Adapter workoutRecyclerViewAdapter;
    private RecyclerView.LayoutManager workoutLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }



}