package com.taylorcfrey.workouttracker.static_content;

import com.taylorcfrey.workouttracker.model.AerobicExercise;
import com.taylorcfrey.workouttracker.model.AnaerobicExercise;
import com.taylorcfrey.workouttracker.model.Exercise;

import java.util.ArrayList;


public class ExerciseContent {

    public static final String RUNNING = "Running";
    public static final String JOGGING = "Jogging";
    public static final String STAIR_CLIMBER = "Stair Climber";
    public static final String ROWING = "Rowing";
    public static final String BARBELL_BENCH_PRESS = "Barbell Bench Press";
    public static final String LAT_PULL_DOWN = "Lat Pull Down";
    public static final String WIDE_PUSH_UP = "Wide Push Up";
    public static final String PLANK = "Plank";

    public static ArrayList<Exercise> EXERCISES = new ArrayList<Exercise>();

    public static void addExercise(Exercise exercise) {
        EXERCISES.add(exercise);
    }

    public static Exercise getExercise(String name) {
        Exercise result = null;
        for (Exercise exercise : EXERCISES) {
            if (exercise.getName().equals(name)) {
                result = exercise;
                break;
            }
        }
        return result;
    }

    static {
        Exercise running = new AerobicExercise(RUNNING);
        running.setIncludeChronometer(true);
        addExercise(running);

        Exercise jogging = new AerobicExercise(JOGGING);
        running.setIncludeChronometer(true);
        addExercise(jogging);

        Exercise stairs = new AerobicExercise(STAIR_CLIMBER);
        running.setIncludeChronometer(true);
        addExercise(stairs);

        Exercise rowing = new AerobicExercise(ROWING);
        running.setIncludeChronometer(true);
        addExercise(rowing);

        Exercise bench = new AnaerobicExercise(BARBELL_BENCH_PRESS);
        addExercise(bench);

        Exercise lat = new AnaerobicExercise(LAT_PULL_DOWN);
        addExercise(lat);

        Exercise pushUp = new AnaerobicExercise(WIDE_PUSH_UP);
        addExercise(pushUp);

        Exercise plank = new AnaerobicExercise(PLANK);
        plank.setIncludeCountDownTimer(true);
        addExercise(plank);
    }
}

