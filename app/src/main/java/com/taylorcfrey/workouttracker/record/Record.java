package com.taylorcfrey.workouttracker.record;

import com.taylorcfrey.workouttracker.record.result.Result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tfrey on 10/18/14.
 */
public class Record implements Serializable {

    private int mId;

    // Name of the Record
    private String mName;

    // Each record will have a list of results
    private List<Result> mResults;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }
}
