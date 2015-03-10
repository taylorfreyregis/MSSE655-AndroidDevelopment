package com.taylorcfrey.workouttracker.record.result;

import android.content.Context;

import com.taylorcfrey.workouttracker.database.WorkoutTrackerDbHelper;
import com.taylorcfrey.workouttracker.record.IRecordService;
import com.taylorcfrey.workouttracker.record.Record;

import java.util.List;

/**
 * Created by tfrey on 10/18/14.
 */
public class ResultService implements IResultService {

    /* Variable Declarations */

    private final static String LOGTAG = "ResultService";

    private WorkoutTrackerDbHelper mDbHelper;

    /* Constructor(s) */

    public ResultService(Context context) {
        mDbHelper = new WorkoutTrackerDbHelper(context);
    }

    /* IService Implementation */

    @Override
    public int create(Result result) {
        return (int)mDbHelper.writeResult(result);
    }

    @Override
    public List<Result> getResults(Record record) {
        return mDbHelper.readResults(record);
    }

    @Override
    public int delete(Result record) {
        return mDbHelper.deleteResult(record);
    }
}
