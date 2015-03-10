package com.taylorcfrey.workouttracker.utils;

import android.app.Application;

import com.taylorcfrey.workouttracker.R;

/**
 * Created a custom application object. This application class will be used for loading the custom
 * font. Eventually, I would like to add custom error handling or something at this level as well.
 */
public class WorkoutTrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TypefaceManager.initialize(this, R.xml.fonts);
    }

}
