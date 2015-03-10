package com.taylorcfrey.workouttracker.record.result;

import com.taylorcfrey.workouttracker.record.Record;

import java.util.List;

public interface IResultService {

    public int create(Result result);

    public List<Result> getResults(Record record);

    public int delete(Result result);
}
