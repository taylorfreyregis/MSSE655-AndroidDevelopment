package com.taylorcfrey.workouttracker.model;

import android.content.Context;
import android.util.Log;

import com.taylorcfrey.workouttracker.database.WorkoutTrackerDbHelper;
import com.taylorcfrey.workouttracker.utils.FileUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for the CRUD operations related to Exercises
 */
public class ExerciseService implements IExerciseService {

    /* Variable Declarations */

    private final static String LOGTAG = "ExerciseService";

    private final String FILE_NAME = "Exercise.wot";

    private List<Exercise> mExercises;
    private WorkoutTrackerDbHelper mDbHelper;

    /* Constructor(s) */

    public ExerciseService(Context context) {
        mDbHelper = new WorkoutTrackerDbHelper(context);
        mExercises = mDbHelper.readExercises();
//        readFile();
    }

    /* IService Implementation */

    @Override
    public Exercise create(Exercise exercise) {
        mExercises.add(exercise);
        mDbHelper.writeExercise(exercise);
//        writeFile();
        return exercise;
    }

    @Override
    public List<Exercise> getExercises() {
        return mExercises = mDbHelper.readExercises();
    }

    @Override
    public int update(Exercise exercise) {
        if(mExercises.contains(exercise)) {
            mExercises.set(mExercises.indexOf(exercise), exercise);
        }
        int rowsAffected = mDbHelper.updateExercise(exercise);

        return rowsAffected;
//        writeFile();
//        return exercise;
    }

    @Override
    public int delete(Exercise exercise) {
        mExercises.remove(exercise);
        return mDbHelper.deleteExercise(exercise);
//        writeFile();
//        return exercise;
    }

    /* Custom Logic */

    private void readFile() {
        String wotPath = FileUtilities.createWotPath(FILE_NAME);
        if (wotPath == null || wotPath.isEmpty()) {
            throw new IllegalStateException("wotPath should not be null or empty!");
        } else {
            mExercises = FileUtilities.readObjectFromPath(wotPath, Exercise.class);
        }

        if (mExercises == null) {
            mExercises = new ArrayList<Exercise>();
        }
    }

    private void writeFile() {
        String wotPath = FileUtilities.createWotPath(FILE_NAME);
        if (wotPath == null || wotPath.isEmpty()) {
            throw new IllegalStateException("wotPath should not be null or empty!");
        } else {
            FileUtilities.writeObjectToPath(mExercises, wotPath);
        }
    }

}
