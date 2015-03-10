package com.taylorcfrey.workouttracker.exercise;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.model.AerobicExercise;
import com.taylorcfrey.workouttracker.model.AnaerobicExercise;
import com.taylorcfrey.workouttracker.model.Exercise;
import com.taylorcfrey.workouttracker.model.ExerciseService;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CreateExerciseFragment extends Fragment {

    private final static String LOGTAG = "CreateExerciseFragment";

    LinearLayout mLayout;

    EditText mNameEditText;
    RadioButton mAerobic;
    RadioButton mAnaerobic;
    ToggleButton mCountdownTimer;
    ToggleButton mStopwatch;
    Button mSave;

    public CreateExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLayout = (LinearLayout) inflater.inflate(R.layout.fragment_create_exercise, container, false);
        mNameEditText = (EditText) mLayout.findViewById(R.id.create_exercise_name_edittext);
        mAerobic = (RadioButton) mLayout.findViewById(R.id.create_exercise_aerobic_radiobutton);
        mAnaerobic = (RadioButton) mLayout.findViewById(R.id.create_exercise_anaerobic_radiobutton);
        mCountdownTimer = (ToggleButton) mLayout.findViewById(R.id.create_exercise_include_timer_togglebutton);
        mStopwatch = (ToggleButton) mLayout.findViewById(R.id.create_exercise_include_stopwatch_togglebutton);
        mSave = (Button) mLayout.findViewById(R.id.button_save_exercise);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExerciseService service = new ExerciseService(getActivity());
                Exercise exercise = null;
                String name = mNameEditText.getText().toString();
                if (mAerobic.isChecked()) {
                    exercise = new AerobicExercise(name);
                } else {
                    exercise = new AnaerobicExercise(name);
                }
                exercise.setIncludeChronometer(mStopwatch.isChecked());
                exercise.setIncludeCountDownTimer(mCountdownTimer.isChecked());

                Log.d(LOGTAG, exercise.toString());

                service.create(exercise);

                FragmentManager manager = getActivity().getFragmentManager();
                manager.popBackStack();
            }
        });

        return mLayout;
    }

}
