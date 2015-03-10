package com.taylorcfrey.workouttracker.model;

public class AerobicExercise extends Exercise {

    private long mDurationInSeconds;

    public AerobicExercise(String name) {
        super(name, true);
    }

    public AerobicExercise(int id, String name) {
        super(id, name, true);
    }

    public long getDurationInSeconds() {
        return mDurationInSeconds;
    }

    public void setDurationInSeconds(long durationInSeconds) {
        this.mDurationInSeconds = durationInSeconds;
    }
}
