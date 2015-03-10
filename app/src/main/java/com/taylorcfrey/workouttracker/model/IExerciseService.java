package com.taylorcfrey.workouttracker.model;

import java.util.List;

/**
 * Interface definition for the ExerciseService
 */
public interface IExerciseService {

    public Exercise create (Exercise exercise);
    public List<Exercise> getExercises();
//    public Exercise update(Exercise exercise);
    public int update(Exercise exercise);
    public int delete(Exercise exercise);
//    public Exercise delete(Exercise exercise);
}
