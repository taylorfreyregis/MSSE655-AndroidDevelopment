package com.taylorcfrey.workouttracker.exercise;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.taylorcfrey.workouttracker.model.Exercise;
import com.taylorcfrey.workouttracker.model.ExerciseService;

public class ExerciseListFragment extends ListFragment {

    private static final String LOGTAG = "ExerciseListFragment";

    ExerciseService mService;
    ArrayAdapter<Exercise> mAdapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExerciseListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mService = new ExerciseService(getActivity());

        mAdapter = new ArrayAdapter<Exercise>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS));
                android.R.layout.simple_list_item_1, android.R.id.text1);

        setListAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        mAdapter.addAll(mService.getExercises());
        mAdapter.notifyDataSetChanged();
        Log.d(LOGTAG, "mAdapter.getCount: " + mAdapter.getCount());
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.clear();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(mService.getExercises().get(position));
        }
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Exercise exercise);
    }

}
