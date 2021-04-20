package com.example.olio_ohjelmointi_harkkatyo_ugh;

public class Exercise {

    private String name;

    private int sets, reps, weights, ID;

    public Exercise(String name, int sets, int reps, int weights, int ID){
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weights = weights;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getWeights() {
        return weights;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeights(int weights) {
        this.weights = weights;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
