package com.taylorcfrey.workouttracker.static_content;

import com.taylorcfrey.workouttracker.model.Routine;

import java.util.ArrayList;

public class RoutineContent {

    public static final String FIVEK_RUN = "5K Run";
    public static final String CHEST = "Chest";
    public static final String CREW = "Crew";

    public static ArrayList<Routine> ROUTINES = new ArrayList<Routine>();

    public static void addRoutine(Routine routine) {
        ROUTINES.add(routine);
    }

    public static Routine getRoutine(String name) {
        Routine result = null;
        for (Routine routine : ROUTINES) {
            if (routine.getName().equals(name)) {
                result = routine;
                break;
            }
        }
        return result;
    }

    static {

        Routine routine5k = new Routine("5K Run");
        routine5k.addExercise(ExerciseContent.getExercise(ExerciseContent.RUNNING));
        addRoutine(routine5k);

        Routine routineChest = new Routine("Chest");
        routineChest.addExercise(ExerciseContent.getExercise(ExerciseContent.BARBELL_BENCH_PRESS));
        routineChest.addExercise(ExerciseContent.getExercise(ExerciseContent.WIDE_PUSH_UP));
        addRoutine(routineChest);

        Routine routineCrew = new Routine("Crew");
        routineCrew.addExercise(ExerciseContent.getExercise(ExerciseContent.ROWING));
        routineCrew.addExercise(ExerciseContent.getExercise(ExerciseContent.LAT_PULL_DOWN));
        routineCrew.addExercise(ExerciseContent.getExercise(ExerciseContent.PLANK));
        addRoutine(routineCrew);
    }
}