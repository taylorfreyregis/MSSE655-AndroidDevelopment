package com.taylorcfrey.workouttracker.model;

import java.io.Serializable;

public class Exercise implements Serializable{

    // Might need the following variable for keeping different versions of serialization synced
    private static final long serialVersionUID = 1L;

    private int mId;

    private boolean mIsAerobic;

    private String mName;

    private boolean mIncludeChronometer = false; // For Stopwatch
    private boolean mIncludeCountDownTimer = false; // For Countdown Timer

    public Exercise(String name) {
        mName = name;
    }

    public Exercise(int id, String name) {
        this(name);
        mId = id;
    }

    public Exercise(String name, boolean isAerobic) {
        this(name);
        mIsAerobic = isAerobic;
    }

    public Exercise(int id, String name, boolean isAerobic) {
        this(name, isAerobic);
        this.mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setIncludeChronometer(boolean include) {
        mIncludeChronometer = include;
    }

    public void setIncludeCountDownTimer(boolean include) {
        mIncludeCountDownTimer = include;
    }

    public boolean hasChronometer() {
        return mIncludeChronometer;
    }

    public boolean hasCountDownTimer() {
        return mIncludeCountDownTimer;
    }

    @Override
    public String toString() {
        return "Exercise: \n" +
                "\nId: " + this.getId() +
                "\nName: " + this.getName() +
                "\nInclude Chronometer: " + mIncludeChronometer +
                "\nInclude CountDownTimer: " + mIncludeCountDownTimer;
    }

    public boolean isAerobic() {
        return mIsAerobic;
    }

    public void setAerobic(boolean isAerobic) {
        mIsAerobic = isAerobic;
    }
}
