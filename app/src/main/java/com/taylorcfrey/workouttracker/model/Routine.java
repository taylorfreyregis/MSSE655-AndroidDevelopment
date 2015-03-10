package com.taylorcfrey.workouttracker.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.taylorcfrey.workouttracker.model.Exercise;

/**
 *
 */
public class Routine implements Serializable{

    // Might need the following variable for keeping different versions of serialization synced
    private static final long serialVersionUID = 1L;

    private String mName;
    private List<Exercise> mExercises;

    public Routine(String name) {
        mName = name;
        mExercises = new ArrayList<Exercise>();
    }

    public Routine(String name, ArrayList<Exercise> exercises) {
        this(name);
        mExercises = exercises;
    }

    public String getName() {
        return mName;
    }

    public void addExercise(Exercise exercise) {
        mExercises.add(exercise);
    }

    public Exercise getExercise(int index) {
        return mExercises.get(index);
    }

    public List<Exercise> getExercises() {
        return mExercises;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
