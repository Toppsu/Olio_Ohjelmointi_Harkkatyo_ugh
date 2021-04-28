package com.example.olio_ohjelmointi_harkkatyo_ugh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {

    private RecyclerView workoutRecyclerView;
    private WorkoutAdapter workoutRecyclerViewAdapter;
    private RecyclerView.LayoutManager workoutLayoutManager;

    private Button addWorkoutButton;
    private String exerciseName,sets,reps,weights;
    private String inputExercise,inputSets,inputReps,inputWeights;
    private ArrayList<Exercise> exerciseArrayList;
    private ArrayList<Exercise> pastExercises;
    private int ID;
    private EditText mEditExercise;
    private EditText mEditSets;
    private EditText mEditReps;
    private EditText mEditWeights;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Line below makes inputing values work by allowing scrolling up the view
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ID = 0;
        setContentView(R.layout.activity_workout);


        //Get past exercises that were added to the dataholder
        pastExercises = DataHolder.getInstance().WorkoutArrayList;
        createExerciseArrayLists();

        //Check if dataholder has contents
        if (pastExercises.isEmpty()!=true){
            exerciseArrayList = pastExercises;
            ID = exerciseArrayList.size();
        }


        buildRecyclerView();

        mEditExercise = (EditText) findViewById(R.id.editExercise);
        mEditSets = (EditText) findViewById(R.id.editSets);
        mEditReps = (EditText) findViewById(R.id.editReps);
        mEditWeights = (EditText) findViewById(R.id.editWeights);


        addWorkoutButton = (Button) findViewById(R.id.addButton);
        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Adds exercise items to arraylist, copies the arraylist to dataholder and notifies the recyclerview abouut changes
                exerciseArrayList.add(new Exercise(mEditExercise.getText().toString(),mEditSets.getText().toString(),mEditReps.getText().toString(),mEditWeights.getText().toString(),ID));
                DataHolder.getInstance().WorkoutArrayList = exerciseArrayList;
                workoutRecyclerViewAdapter.notifyItemInserted(ID);
                ID = ID+1;

            }
        });

    }

    public void createExerciseArrayLists(){
        exerciseArrayList = new ArrayList<Exercise>();
    }

    public void buildRecyclerView(){
        //Building of the recyclerView by applying guide found on youtube: https://www.youtube.com/playlist?list=PLrnPJCHvNZuBtTYUuc5Pyo4V7xZ2HNtf4
        workoutRecyclerView = findViewById(R.id.workoutRecyclerView);
        workoutRecyclerView.setHasFixedSize(true);
        workoutLayoutManager = new LinearLayoutManager(this);
        workoutRecyclerViewAdapter = new WorkoutAdapter(exerciseArrayList);
        workoutRecyclerView.setLayoutManager(workoutLayoutManager);
        workoutRecyclerView.setAdapter(workoutRecyclerViewAdapter);

        //Removes items same way as adding
        workoutRecyclerViewAdapter.setOnItemClickListener(new WorkoutAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                exerciseArrayList.remove(position);
                DataHolder.getInstance().WorkoutArrayList = exerciseArrayList;
                workoutRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

}