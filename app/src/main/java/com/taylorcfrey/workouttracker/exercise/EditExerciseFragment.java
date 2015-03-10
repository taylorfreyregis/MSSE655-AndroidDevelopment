package com.taylorcfrey.workouttracker.exercise;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.model.AerobicExercise;
import com.taylorcfrey.workouttracker.model.AnaerobicExercise;
import com.taylorcfrey.workouttracker.model.Exercise;
import com.taylorcfrey.workouttracker.model.ExerciseService;

/**
 * Created by tfrey on 10/18/14.
 */
public class EditExerciseFragment extends Fragment {

    private final static String LOGTAG = "CreateExerciseFragment";

    private final static String EXTRA_EXERCISE = "com.taylorcfrey.workouttracker.exercise.editexercise";

    LinearLayout mLayout;

    Exercise mExercise;

    TextView mName;
    RadioButton mAerobic;
    RadioButton mAnaerobic;
    ToggleButton mCountdownTimer;
    ToggleButton mStopwatch;
    Button mUpdate;
    Button mDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mExercise = (Exercise)this.getArguments().getSerializable(EXTRA_EXERCISE);
        if (mExercise == null) {
            throw new IllegalArgumentException("Must use .newInstance(Exercise) method with a non-null" +
                    " Exercise");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLayout = (LinearLayout) inflater.inflate(R.layout.fragment_edit_exercise, container, false);
        mName = (TextView) mLayout.findViewById(R.id.edit_exercise_name_textview);
        mName.setText(mExercise.getName());
        mAerobic = (RadioButton) mLayout.findViewById(R.id.edit_exercise_aerobic_radiobutton);
        mAnaerobic = (RadioButton) mLayout.findViewById(R.id.edit_exercise_anaerobic_radiobutton);

        if (mExercise instanceof AerobicExercise) {
            mAerobic.setChecked(true);
        } else {
            mAnaerobic.setChecked(true);
        }

        mCountdownTimer = (ToggleButton) mLayout.findViewById(R.id.edit_exercise_include_timer_togglebutton);

        if (mExercise.hasCountDownTimer()) {
            mCountdownTimer.setChecked(true);
        }

        mStopwatch = (ToggleButton) mLayout.findViewById(R.id.edit_exercise_include_stopwatch_togglebutton);

        if (mExercise.hasChronometer()) {
            mStopwatch.setChecked(true);
        }

        mUpdate = (Button) mLayout.findViewById(R.id.button_update_exercise);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExerciseService service = new ExerciseService(getActivity());
                Exercise exercise = null;
                String name = mExercise.getName();
                if (mAerobic.isChecked()) {
                    exercise = new AerobicExercise(name);
                } else {
                    exercise = new AnaerobicExercise(name);
                }
                exercise.setIncludeChronometer(mStopwatch.isChecked());
                exercise.setIncludeCountDownTimer(mCountdownTimer.isChecked());
                exercise.setId(mExercise.getId());

                Log.d(LOGTAG, exercise.toString());

                service.update(exercise);

                FragmentManager manager = getActivity().getFragmentManager();
                manager.popBackStack();
            }
        });

        mDelete = (Button) mLayout.findViewById(R.id.button_delete_exercise);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExerciseService service = new ExerciseService(getActivity());
                service.delete(mExercise);

                FragmentManager manager = getActivity().getFragmentManager();
                manager.popBackStack();
            }
        });

        return mLayout;
    }

    public static EditExerciseFragment getInstance(Exercise exercise) {
        EditExerciseFragment fragment = new EditExerciseFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_EXERCISE, exercise);
        fragment.setArguments(arguments);
        return fragment;
    }

}