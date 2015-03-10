package com.taylorcfrey.workouttracker.routine;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.taylorcfrey.workouttracker.R;

import com.taylorcfrey.workouttracker.model.Exercise;
import com.taylorcfrey.workouttracker.model.Routine;
import com.taylorcfrey.workouttracker.static_content.RoutineContent;

/**
 * A fragment representing a single Routine detail screen.
 * This fragment is either contained in a {@link RoutineListActivity}
 * in two-pane mode (on tablets) or a {@link RoutineDetailActivity}
 * on handsets.
 */
public class RoutineDetailFragment extends Fragment {

    private static final String LOGTAG = "RoutineDetailFragment";

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ROUTINE_NAME = "routine_name";

    /**
     * The routine this fragment is presenting
     */
    private Routine mRoutine;

    private TextView mRoutineTitle;
    private ListView mExercisesListView;
    private ArrayAdapter<Exercise> mExerciseAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RoutineDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ROUTINE_NAME)) {
            mRoutine = RoutineContent.getRoutine(getArguments().getString(ROUTINE_NAME));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_routine_detail_list, container, false);


        if (mRoutine != null) {

            mRoutineTitle = (TextView) rootView.findViewById(R.id.routine_detail);
            mRoutineTitle.setText(mRoutine.getName());

            mExercisesListView = (ListView) rootView.findViewById(R.id.routine_exercise_listview);

            mExerciseAdapter = new RoutineDetailAdapter(getActivity(),
                    R.layout.list_item_exercise,
                    mRoutine.getExercises());

//            mExerciseAdapter = new ArrayAdapter<Exercise>(getActivity(),
//                    R.layout.list_item_exercise,
//                    R.id.routine_detail_exercise_name_textview,
//                    mRoutine.getExercises());

            Log.d(LOGTAG, "mRoutine.getExercises.size(): " + mRoutine.getExercises().size());

            mExercisesListView.setAdapter(mExerciseAdapter);

            mExercisesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // For the time being, this is just representative of the potential event.

                    Toast.makeText(getActivity(),
                            ((Exercise) mExercisesListView.getItemAtPosition(position)).getName(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }

        return rootView;
    }
}
