package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    private RecyclerView workoutRecyclerView;
    private RecyclerView.Adapter workoutRecyclerViewAdapter;
    private RecyclerView.LayoutManager workoutLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));
        exerciseArrayList.add(new Exercise("Liike","3","12","50", 1));



        workoutRecyclerView = findViewById(R.id.workoutRecyclerView);
        //workoutRecyclerView.setHasFixedSize(true);
        workoutLayoutManager = new LinearLayoutManager(this);
        workoutRecyclerViewAdapter = new WorkoutAdapter(exerciseArrayList);

        workoutRecyclerView.setLayoutManager(workoutLayoutManager);
        workoutRecyclerView.setAdapter(workoutRecyclerViewAdapter);
    }



}