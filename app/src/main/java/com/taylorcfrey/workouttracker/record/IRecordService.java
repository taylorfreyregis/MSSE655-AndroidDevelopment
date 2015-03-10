package com.taylorcfrey.workouttracker.record;

import java.util.List;

/**
 * Created by tfrey on 10/18/14.
 */
public interface IRecordService {

    public int create (Record record);

    public List<Record> getRecords();

    public int delete(Record record);
}
