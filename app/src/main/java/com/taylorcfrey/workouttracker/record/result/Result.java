package com.taylorcfrey.workouttracker.record.result;

import java.util.GregorianCalendar;

/**
 * Created by tfrey on 10/18/14.
 */
public class Result {

    private int mId;

    private int mRecordId;

    // When it occurred / was measured
    private GregorianCalendar mDate;

    // The value. Ideally more specific, but this will work given the time constraints
    private int mValue;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getRecordId() {
        return mRecordId;
    }

    public void setRecordId(int recordId) {
        mRecordId = recordId;
    }

    public GregorianCalendar getDate() {
        return mDate;
    }

    public void setDate(GregorianCalendar date) {
        mDate = date;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }
}
