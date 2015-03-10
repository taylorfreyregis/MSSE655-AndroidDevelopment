package com.taylorcfrey.workouttracker.record.result;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.record.Record;
import com.taylorcfrey.workouttracker.utils.Utils;

import java.util.List;

/**
 * ArrayAdapter would work, but this will allow for greater customization in the future.
 */
public class ResultAdapter extends BaseAdapter {

    private static final String LOGTAG = "RecordAdapter";
    private Context mContext;
    private List<Result> mResults;
    private int mListItemResourceId;

    public ResultAdapter(Context context, int resourceId, List<Result> results) {
        this.mContext = context;
        this.mListItemResourceId = resourceId;
        this.mResults = results;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public Object getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ResultViewHolder resultViewHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            resultViewHolder = new ResultViewHolder();
            view = inflater.inflate(mListItemResourceId, parent, false);

            resultViewHolder.date = (TextView) view.findViewById(R.id.text_view_result_date);
            resultViewHolder.value = (TextView) view.findViewById(R.id.text_view_result_value);

            view.setTag(resultViewHolder);
        } else {
            resultViewHolder = (ResultViewHolder) view.getTag();
        }

        Result result = (Result) this.mResults.get(position);
        resultViewHolder.date.setText(Utils.parseCalendarToSql(result.getDate()));
        resultViewHolder.value.setText(Integer.toString(result.getValue()));

        return view;
    }

    private static class ResultViewHolder {
        TextView date;
        TextView value;
    }
}
