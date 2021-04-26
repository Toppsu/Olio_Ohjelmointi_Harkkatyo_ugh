package com.example.olio_ohjelmointi_harkkatyo_ugh;

public class Exercise {

    private String name, sets, reps, weights;

    private int ID;

    public Exercise(String name, String sets, String reps, String weights, int ID){
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weights = weights;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getSets() {
        return sets;
    }

    public String getReps() {
        return reps;
    }

    public String getWeights() {
        return weights;
    }

    public int getID() { return ID; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
