package com.taylorcfrey.workouttracker.database;


import android.provider.BaseColumns;

/**
 * Contract class used for defining the schema of the database and enforcing type.
 */
public class WorkoutTrackerContract {

    // Used to prevent accidental creation of this class
    private WorkoutTrackerContract() {}

    // Table for all types of Exercises, no truly necessary need to separate Aerobic from Anaerobic
    public static abstract class ExerciseEntry implements BaseColumns {
        public static final String TABLE_NAME = "exercise";
        public static final String COLUMN_NAME_EXERCISE_NAME = "name";
        public static final String COLUMN_NAME_AEROBIC = "aerobic";
        public static final String COLUMN_NAME_CHRONOMETER = "chronometer";
        public static final String COLUMN_NAME_TIMER = "timer";
        public static final String COLUMN_NAME_NULLABLE = "nullable";
    }

    // Table for Records
    public static abstract class RecordEntry implements BaseColumns {
        public static final String TABLE_NAME = "record";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NULLABLE = "nullable";
    }

    // Table for Records
    public static abstract class ResultEntry implements BaseColumns {
        public static final String TABLE_NAME = "result";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_RECORD_ID = "recordId";
        public static final String COLUMN_NAME_NULLABLE = "nullable";
    }
}
