package com.taylorcfrey.workouttracker.record;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taylorcfrey.workouttracker.R;

import java.util.List;

/**
 * ArrayAdapter would work, but this will allow for greater customization in the future.
 */
public class RecordAdapter extends BaseAdapter {

    private static final String LOGTAG = "RecordAdapter";
    private Context mContext;
    private List<Record> mRecords;
    private int mListItemResourceId;

    public RecordAdapter(Context context, int resourceId, List<Record> records) {
        this.mContext = context;
        this.mListItemResourceId = resourceId;
        this.mRecords = records;
    }

    @Override
    public int getCount() {
        return mRecords.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRecords.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecordViewHolder recordViewHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            recordViewHolder = new RecordViewHolder();
            view = inflater.inflate(mListItemResourceId, parent, false);

            recordViewHolder.title = (TextView) view.findViewById(R.id.text_view_record_list_item);

            view.setTag(recordViewHolder);
        } else {
            recordViewHolder = (RecordViewHolder) view.getTag();
        }

        Record record = (Record) this.mRecords.get(position);
        recordViewHolder.title.setText(record.getName());

        return view;
    }

    private static class RecordViewHolder {
        TextView title;
    }
}
