package com.taylorcfrey.workouttracker.model;

import java.util.List;

/**
 * Responsible for the CRUD operations related to Routines
 */
public class RoutineService implements IRoutineService {

    /* Variable Declarations */

    private final static String LOGTAG = "RoutineService";

    private final String FILE_NAME = "Service.wot";

    private List<Routine> mRoutines;

    /* Constructor(s) */

    public RoutineService() {
        readFile();
    }

    /* IService Implementation */

    @Override
    public Routine create(Routine routine) {
        mRoutines.add(routine);
        writeFile();
        return routine;
    }

    @Override
    public List<Routine> retrieveAllRoutines() {
        return mRoutines;
    }

    @Override
    public Routine update(Routine routine) {
        if(mRoutines.contains(routine)) {
            // Update exercise
        }

        writeFile();
        return routine;
    }

    @Override
    public Routine delete(Routine routine) {
        mRoutines.remove(routine);
        writeFile();
        return routine;
    }

    /* Custom Logic */

    private void readFile() {

    }

    private void writeFile() {

    }
}
