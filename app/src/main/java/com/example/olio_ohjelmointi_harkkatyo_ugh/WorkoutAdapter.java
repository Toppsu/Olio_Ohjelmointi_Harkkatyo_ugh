package com.example.olio_ohjelmointi_harkkatyo_ugh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private ArrayList<Exercise> mExerciseArrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder{

        public TextView exerciseTextView;
        public TextView setsTextView;
        public TextView repsTextView;
        public TextView weightsTextView;
        public TextView positionTextView;
        public ImageView deleteImage;

        public WorkoutViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            exerciseTextView = itemView.findViewById(R.id.exerciseName);
            setsTextView = itemView.findViewById(R.id.sets);
            repsTextView = itemView.findViewById(R.id.reps);
            weightsTextView = itemView.findViewById(R.id.weights);
            positionTextView = itemView.findViewById(R.id.position);
            deleteImage = itemView.findViewById(R.id.image_delete);



            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public WorkoutAdapter(ArrayList<Exercise> exerciseArrayList){
        mExerciseArrayList = exerciseArrayList;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item,parent,false);
        WorkoutViewHolder wvh = new WorkoutViewHolder(v,mListener);
        return wvh;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Exercise currentItem = mExerciseArrayList.get(position);
        holder.exerciseTextView.setText(currentItem.getName());
        holder.setsTextView.setText(currentItem.getSets());
        holder.repsTextView.setText(currentItem.getReps());
        holder.weightsTextView.setText(currentItem.getWeights()+"kg");
        holder.positionTextView.setText(String.valueOf(position+1)+".");
    }

    @Override
    public int getItemCount() {
        return mExerciseArrayList.size();
    }

}
