package com.taylorcfrey.workouttracker.routine;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.model.Exercise;

import java.util.List;

/**
 * Created by taylorfrey on 10/6/14.
 */
public class RoutineDetailAdapter extends ArrayAdapter<Exercise> {

    private Context mContext;
    private List<Exercise> mExercises;
    private int mResourceId;

    public RoutineDetailAdapter(Context context, int resource) {
        super(context, resource);
    }

    public RoutineDetailAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public RoutineDetailAdapter(Context context, int resource, Exercise[] objects) {
        super(context, resource, objects);
    }

    public RoutineDetailAdapter(Context context, int resource, int textViewResourceId, Exercise[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public RoutineDetailAdapter(Context context, int resource, List<Exercise> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourceId = resource;
        this.mExercises = objects;
    }

    public RoutineDetailAdapter(Context context, int resource, int textViewResourceId, List<Exercise> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RoutineDetailViewHolder mRoutineDetailViewHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            mRoutineDetailViewHolder = new RoutineDetailViewHolder();

            view = inflater.inflate(mResourceId, parent, false);
            mRoutineDetailViewHolder.exerciseName = (TextView) view
                    .findViewById(R.id.routine_detail_exercise_name_textview);

            view.setTag(mRoutineDetailViewHolder);

        } else {
            mRoutineDetailViewHolder = (RoutineDetailViewHolder) view.getTag();

        }

        Exercise exercise = (Exercise) this.mExercises.get(position);

        mRoutineDetailViewHolder.exerciseName.setText(exercise.getName());

        return view;
    }

    // ViewHolder pattern
    private static class RoutineDetailViewHolder {
        TextView exerciseName;
    }
}
