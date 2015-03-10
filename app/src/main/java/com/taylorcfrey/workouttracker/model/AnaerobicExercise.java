package com.taylorcfrey.workouttracker.model;

public class AnaerobicExercise extends Exercise {

    private int mWeight;

    private int mRepetitions;

    public AnaerobicExercise(String name) {
        super(name, false);
    }

    public AnaerobicExercise(int id, String name) {
        super(id, name, false);
    }

    public int getRepetitions() {
        return mRepetitions;
    }

    public void setRepetitions(int repetitions) {
        this.mRepetitions = repetitions;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int weight) {
        this.mWeight = weight;
    }
}
