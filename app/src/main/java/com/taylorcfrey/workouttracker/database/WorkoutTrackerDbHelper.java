package com.taylorcfrey.workouttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.taylorcfrey.workouttracker.model.AerobicExercise;
import com.taylorcfrey.workouttracker.model.AnaerobicExercise;
import com.taylorcfrey.workouttracker.record.Record;
import com.taylorcfrey.workouttracker.record.result.Result;
import com.taylorcfrey.workouttracker.utils.Utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Helper class for utilizing SQLite.
 */
public class WorkoutTrackerDbHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "WorkoutTrackerDbHelper";

    /* -------- SQL DB INFO -------- */
    public static final int DATABASE_VERSION = 204;
    public static final String DATABASE_NAME = "WorkoutTracker.db";

    /* -------- SQL COMMANDS -------- */
    private final static String TEXT_TYPE = " TEXT";
    private final static String INTEGER_TYPE = " INTEGER";
    private final static String COMMA_SEPARATOR = ",";
    private final static String SEMI_COL = ";";
    private final static String OPEN_PAREN = "(";
    private final static String CLOSE_PAREN = ")";

    /* -------- Exercise -------- */
    private final static String SQL_CREATE_TABLE_EXERCISE =
            "CREATE TABLE " + WorkoutTrackerContract.ExerciseEntry.TABLE_NAME + OPEN_PAREN +
                    WorkoutTrackerContract.ExerciseEntry._ID + " INTEGER PRIMARY KEY," +
                    WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME + TEXT_TYPE + COMMA_SEPARATOR +
                    WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC + INTEGER_TYPE + COMMA_SEPARATOR +
                    WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER + INTEGER_TYPE + COMMA_SEPARATOR +
                    WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER + INTEGER_TYPE + CLOSE_PAREN + SEMI_COL;

    private final static String SQL_DROP_TABLE_EXERCISE =
            "DROP TABLE IF EXISTS " + WorkoutTrackerContract.ExerciseEntry.TABLE_NAME + SEMI_COL;

    /* -------- Records -------- */

    private final static String SQL_CREATE_TABLE_RECORDS =
            "CREATE TABLE " + WorkoutTrackerContract.RecordEntry.TABLE_NAME + OPEN_PAREN +
                    WorkoutTrackerContract.RecordEntry._ID + " " + INTEGER_TYPE + " " + "PRIMARY KEY" + COMMA_SEPARATOR +
                    WorkoutTrackerContract.RecordEntry.COLUMN_NAME_NAME + TEXT_TYPE + CLOSE_PAREN + SEMI_COL;

    private final static String SQL_DROP_TABLE_RECORDS =
            "DROP TABLE IF EXISTS " + WorkoutTrackerContract.RecordEntry.TABLE_NAME + SEMI_COL;

    /* -------- Results -------- */

    private final static String SQL_CREATE_TABLE_RESULTS =
            "CREATE TABLE " + WorkoutTrackerContract.ResultEntry.TABLE_NAME + OPEN_PAREN +
                    WorkoutTrackerContract.ResultEntry._ID + " " + INTEGER_TYPE + " " + "PRIMARY KEY" + COMMA_SEPARATOR +
                    WorkoutTrackerContract.ResultEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEPARATOR +
                    WorkoutTrackerContract.ResultEntry.COLUMN_NAME_VALUE + INTEGER_TYPE + COMMA_SEPARATOR +
                    WorkoutTrackerContract.ResultEntry.COLUMN_NAME_RECORD_ID + INTEGER_TYPE + COMMA_SEPARATOR +
            " FOREIGN KEY " + OPEN_PAREN + WorkoutTrackerContract.ResultEntry.COLUMN_NAME_RECORD_ID + CLOSE_PAREN + " REFERENCES " +
                    WorkoutTrackerContract.RecordEntry.TABLE_NAME + " " + OPEN_PAREN + WorkoutTrackerContract.RecordEntry._ID + CLOSE_PAREN
                    + CLOSE_PAREN + SEMI_COL; // Foreign Key

    private final static String SQL_DROP_TABLE_RESULTS =
            "DROP TABLE IF EXISTS " + WorkoutTrackerContract.ResultEntry.TABLE_NAME + SEMI_COL;

    /* -------- Constructor(s) -------- */
    public WorkoutTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* -------- SQL Helper Overrides -------- */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOGTAG, "onCreate: " + SQL_CREATE_TABLE_EXERCISE);
        db.execSQL(SQL_CREATE_TABLE_EXERCISE);
        db.execSQL(SQL_CREATE_TABLE_RECORDS);
        Log.d(LOGTAG, SQL_CREATE_TABLE_RESULTS);
        db.execSQL(SQL_CREATE_TABLE_RESULTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(LOGTAG, "onUpgrade: " + db + " oldVersion: " + oldVersion + " newVersion: " + newVersion);
        db.execSQL(SQL_DROP_TABLE_EXERCISE);
        db.execSQL(SQL_DROP_TABLE_RECORDS);
        db.execSQL(SQL_DROP_TABLE_RESULTS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Don't really care about downgrading, however for consistency, let's start anew
        onUpgrade(db, oldVersion, newVersion);
    }

    /* -------- SQL Exercise Commands -------- */
    //  Returns id of exercise
    public long writeExercise(com.taylorcfrey.workouttracker.model.Exercise exercise) {

        // get writable db
        SQLiteDatabase database = this.getWritableDatabase();

        // create value object
        ContentValues exerciseContentValues = new ContentValues();
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME, exercise.getName()
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC, exercise.isAerobic()
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER, exercise.hasChronometer()
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER, exercise.hasCountDownTimer()
        );

        // insert
        return database.insert(
                WorkoutTrackerContract.ExerciseEntry.TABLE_NAME,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_NULLABLE,
                exerciseContentValues);
    }

    // Returns number of rows updated
    public int updateExercise(com.taylorcfrey.workouttracker.model.Exercise exercise) {

        Log.d(LOGTAG, "updateExercise: " + exercise);

        SQLiteDatabase database = this.getWritableDatabase();

        // create value object
        ContentValues exerciseContentValues = new ContentValues();
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME, exercise.getName()
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC, exercise.isAerobic()
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER, exercise.hasChronometer()
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER, exercise.hasCountDownTimer()
        );

        // WHERE
        String where = WorkoutTrackerContract.ExerciseEntry._ID + " = ?";

        return database.update(
                WorkoutTrackerContract.ExerciseEntry.TABLE_NAME,
                exerciseContentValues,
                where,
                new String[]{Integer.toString(exercise.getId())}
        );
    }

    // Returns number of rows updated
    public int deleteExercise(com.taylorcfrey.workouttracker.model.Exercise exercise) {

        Log.d(LOGTAG, "deleteExercise: " + exercise);

        SQLiteDatabase database = this.getWritableDatabase();

        // WHERE
        String where = WorkoutTrackerContract.ExerciseEntry._ID + " = ?";

        return database.delete(
                WorkoutTrackerContract.ExerciseEntry.TABLE_NAME,
                where,
                new String[]{Integer.toString(exercise.getId())}
        );
    }

    public List<com.taylorcfrey.workouttracker.model.Exercise> readExercises() {

        Log.d(LOGTAG, "readExercises");

        // Sort order
        String sortOrder = WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME + " ASC";

        // get readable db
        SQLiteDatabase database = this.getReadableDatabase();

        // a projection is really just a list of the column names to query.
        String[] projection = {
                WorkoutTrackerContract.ExerciseEntry._ID,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER
        };

        Cursor cursor = database.query(
                true, // DISTINCT
                WorkoutTrackerContract.ExerciseEntry.TABLE_NAME, // FROM Table
                projection, // SELECT column names
                null, // WHERE columns
                null, // WHERE values
                null, // GROUP BY
                null, // HAVING
                sortOrder, // ORDER BY
                null, // LIMIT
                null); // cancel

        cursor.moveToFirst();
        List<com.taylorcfrey.workouttracker.model.Exercise> exercises = new ArrayList<com.taylorcfrey.workouttracker.model.Exercise>();
        for (int i = 0; i < cursor.getCount(); i++) {

            com.taylorcfrey.workouttracker.model.Exercise exercise;


            int id = (int)cursor.getLong(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry._ID)
            );

            boolean isAerobic = cursor.getInt(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC)
            ) != 0;

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME)
            );

            boolean hasChronometer = cursor.getInt(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER)
            ) != 0;

            boolean hasTimer = cursor.getInt(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER)
            ) != 0;

            if (isAerobic) {
                exercise = new AerobicExercise(id, name);
            } else {
                exercise = new AnaerobicExercise(id, name);
            }

            exercise.setIncludeChronometer(hasChronometer);
            exercise.setIncludeCountDownTimer(hasTimer);

            exercises.add(exercise);
            cursor.moveToNext();
        }

        return exercises;
    }

    public com.taylorcfrey.workouttracker.model.Exercise readExercise(int id) {

        Log.d(LOGTAG, "readExercise(int): " + id);

        // Sort order
        String sortOrder = WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME + " ASC";

        // WHERE
        String where = WorkoutTrackerContract.ExerciseEntry._ID + " = ?";

        // get readable db
        SQLiteDatabase database = this.getReadableDatabase();

        // a projection is really just a list of the column names to query.
        String[] projection = {
                WorkoutTrackerContract.ExerciseEntry._ID,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER
        };

        Cursor cursor = database.query(
                true, // DISTINCT
                WorkoutTrackerContract.ExerciseEntry.TABLE_NAME, // FROM Table
                projection, // SELECT column names
                where, // WHERE columns
                new String[]{Integer.toString(id)}, // WHERE values
                null, // GROUP BY
                null, // HAVING
                sortOrder, // ORDER BY
                null, // LIMIT
                null); // cancel

        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return null;
        }

        com.taylorcfrey.workouttracker.model.Exercise exercise;

        int exerciseId = (int)cursor.getLong(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry._ID)
        );

        boolean isAerobic = cursor.getInt(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC)
        ) != 0;

        boolean hasChronometer = cursor.getInt(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER)
        ) != 0;

        boolean hasTimer = cursor.getInt(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER)
        ) != 0;

        String name = cursor.getString(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME)
        );

        if (isAerobic) {
            exercise = new AerobicExercise(exerciseId, name);
        } else {
            exercise = new AnaerobicExercise(exerciseId, name);
        }

        exercise.setIncludeChronometer(hasChronometer);
        exercise.setIncludeCountDownTimer(hasTimer);

        return exercise;
    }

    public com.taylorcfrey.workouttracker.model.Exercise readExercise(String exerciseName) {

        Log.d(LOGTAG, "readExercise(String): " + exerciseName);

        // Sort order
        String sortOrder = WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME + " ASC";

        // WHERE
        String where = WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME + " LIKE ?";

        // get readable db
        SQLiteDatabase database = this.getReadableDatabase();

        // a projection is really just a list of the column names to query.
        String[] projection = {
                WorkoutTrackerContract.ExerciseEntry._ID,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER,
                WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER
        };

        Cursor cursor = database.query(
                true, // DISTINCT
                WorkoutTrackerContract.ExerciseEntry.TABLE_NAME, // FROM Table
                projection, // SELECT column names
                where, // WHERE columns
                new String[]{exerciseName}, // WHERE values
                null, // GROUP BY
                null, // HAVING
                sortOrder, // ORDER BY
                null, // LIMIT
                null); // cancel

        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            return null;
        }

        com.taylorcfrey.workouttracker.model.Exercise exercise;

        int exerciseId = (int)cursor.getLong(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry._ID)
        );

        boolean isAerobic = cursor.getInt(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_AEROBIC)
        ) != 0;

        boolean hasChronometer = cursor.getInt(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_CHRONOMETER)
        ) != 0;

        boolean hasTimer = cursor.getInt(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_TIMER)
        ) != 0;

        String name = cursor.getString(
                cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ExerciseEntry.COLUMN_NAME_EXERCISE_NAME)
        );

        if (isAerobic) {
            exercise = new AerobicExercise(exerciseId, name);
        } else {
            exercise = new AnaerobicExercise(exerciseId, name);
        }

        exercise.setIncludeChronometer(hasChronometer);
        exercise.setIncludeCountDownTimer(hasTimer);

        return exercise;
    }

    /* -------- Records -------- */
    public long writeRecord(Record record) {

        // get writable db
        SQLiteDatabase database = this.getWritableDatabase();

        // create value object
        ContentValues exerciseContentValues = new ContentValues();
        exerciseContentValues.put(
                WorkoutTrackerContract.RecordEntry.COLUMN_NAME_NAME, record.getName()
        );

        // insert
        return database.insert(
                WorkoutTrackerContract.RecordEntry.TABLE_NAME,
                WorkoutTrackerContract.RecordEntry.COLUMN_NAME_NULLABLE,
                exerciseContentValues);
    }

    public List<Record> readRecords() {

        // Sort order
        String sortOrder = WorkoutTrackerContract.RecordEntry.COLUMN_NAME_NAME + " ASC";

        // get readable db
        SQLiteDatabase database = this.getReadableDatabase();

        // a projection is really just a list of the column names to query.
        String[] projection = {
                WorkoutTrackerContract.RecordEntry._ID,
                WorkoutTrackerContract.RecordEntry.COLUMN_NAME_NAME
        };

        Cursor cursor = database.query(
                true, // DISTINCT
                WorkoutTrackerContract.RecordEntry.TABLE_NAME, // FROM Table
                projection, // SELECT column names
                null, // WHERE columns
                null, // WHERE values
                null, // GROUP BY
                null, // HAVING
                sortOrder, // ORDER BY
                null, // LIMIT
                null); // cancel

        cursor.moveToFirst();
        List<Record> records = new ArrayList<Record>();
        for (int i = 0; i < cursor.getCount(); i++) {

            Record record = new Record();

            int id = (int)cursor.getLong(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.RecordEntry._ID)
            );

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.RecordEntry.COLUMN_NAME_NAME)
            );

            record.setId(id);
            record.setName(name);

            record.setResults(this.readResults(record));

            records.add(record);
            cursor.moveToNext();
        }

        return records;
    }

    // Returns number of rows updated
    public int deleteRecord(Record record) {

        SQLiteDatabase database = this.getWritableDatabase();

        // WHERE
        String where = WorkoutTrackerContract.RecordEntry._ID + " = ?";

        // Delete all results associated with the Record
        for (Result result : record.getResults()) {
            this.deleteResult(result);
        }

        return database.delete(
                WorkoutTrackerContract.RecordEntry.TABLE_NAME,
                where,
                new String[]{Integer.toString(record.getId())}
        );
    }

    /* -------- Records -------- */
    public long writeResult(Result result) {

        // get writable db
        SQLiteDatabase database = this.getWritableDatabase();

        // create value object
        ContentValues exerciseContentValues = new ContentValues();
        exerciseContentValues.put(
                WorkoutTrackerContract.ResultEntry.COLUMN_NAME_DATE, Utils.parseCalendarToSql(result.getDate())
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ResultEntry.COLUMN_NAME_VALUE, result.getValue()
        );
        exerciseContentValues.put(
                WorkoutTrackerContract.ResultEntry.COLUMN_NAME_RECORD_ID, result.getRecordId()
        );

        // insert
        return database.insert(
                WorkoutTrackerContract.ResultEntry.TABLE_NAME,
                WorkoutTrackerContract.ResultEntry.COLUMN_NAME_NULLABLE,
                exerciseContentValues);
    }

    //TODO should be a JOIN instead of a where... utilize the foreign key constraint
    public List<Result> readResults(Record record) {

        // Sort order
        String sortOrder = WorkoutTrackerContract.ResultEntry.COLUMN_NAME_DATE + " ASC";

        // get readable db
        SQLiteDatabase database = this.getReadableDatabase();

        // a projection is really just a list of the column names to query.
        String[] projection = {
                WorkoutTrackerContract.ResultEntry._ID,
                WorkoutTrackerContract.ResultEntry.COLUMN_NAME_DATE,
                WorkoutTrackerContract.ResultEntry.COLUMN_NAME_VALUE,
                WorkoutTrackerContract.ResultEntry.COLUMN_NAME_RECORD_ID
        };

        // WHERE
        String where = WorkoutTrackerContract.ResultEntry.COLUMN_NAME_RECORD_ID + " = ?";

        String[] wheres = new String[]{Integer.toString(record.getId())};

        Cursor cursor = database.query(
                true, // DISTINCT
                WorkoutTrackerContract.ResultEntry.TABLE_NAME, // FROM Table
                projection, // SELECT column names
                where, // WHERE columns
                wheres, // WHERE values
                null, // GROUP BY
                null, // HAVING
                sortOrder, // ORDER BY
                null, // LIMIT
                null); // cancel

        cursor.moveToFirst();
        List<Result> results = new ArrayList<Result>();
        for (int i = 0; i < cursor.getCount(); i++) {

            Result result = new Result();

            int id = (int)cursor.getLong(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ResultEntry._ID)
            );

            String sqlDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ResultEntry.COLUMN_NAME_DATE)
            );

            int value = cursor.getInt(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ResultEntry.COLUMN_NAME_VALUE)
            );

            int recordId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(WorkoutTrackerContract.ResultEntry.COLUMN_NAME_RECORD_ID)
            );

            result.setId(id);
            result.setDate(Utils.parseSqlToCalendar(sqlDate));
            result.setValue(value);
            result.setRecordId(recordId);

            results.add(result);
            cursor.moveToNext();
        }

        return results;
    }

    // Returns number of rows updated
    public int deleteResult(Result result) {

        SQLiteDatabase database = this.getWritableDatabase();

        // WHERE
        String where = WorkoutTrackerContract.ResultEntry._ID + " = ?";

        return database.delete(
                WorkoutTrackerContract.ResultEntry.TABLE_NAME,
                where,
                new String[]{Integer.toString(result.getId())}
        );
    }
}
