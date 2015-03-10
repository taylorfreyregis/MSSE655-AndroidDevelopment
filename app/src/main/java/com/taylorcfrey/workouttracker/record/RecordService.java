package com.taylorcfrey.workouttracker.record;

import android.content.Context;

import com.taylorcfrey.workouttracker.database.WorkoutTrackerDbHelper;
import com.taylorcfrey.workouttracker.model.Exercise;
import com.taylorcfrey.workouttracker.model.IExerciseService;
import com.taylorcfrey.workouttracker.utils.FileUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tfrey on 10/18/14.
 */
public class RecordService implements IRecordService {

    /* Variable Declarations */

    private final static String LOGTAG = "RecordService";

    private WorkoutTrackerDbHelper mDbHelper;

    /* Constructor(s) */

    public RecordService(Context context) {
        mDbHelper = new WorkoutTrackerDbHelper(context);
    }

    /* IService Implementation */

    @Override
    public int create(Record record) {
        return (int)mDbHelper.writeRecord(record);
    }

    @Override
    public List<Record> getRecords() {
        return mDbHelper.readRecords();
    }

    @Override
    public int delete(Record record) {
        return mDbHelper.deleteRecord(record);
    }
}
