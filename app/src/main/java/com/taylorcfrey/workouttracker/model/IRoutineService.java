package com.taylorcfrey.workouttracker.model;

import java.util.List;

/**
 * Created by taylorfrey on 10/5/14.
 */
public interface IRoutineService {

    public Routine create (Routine routine);
    public List<Routine> retrieveAllRoutines();
    public Routine update(Routine routine);
    public Routine delete(Routine routine);
}
