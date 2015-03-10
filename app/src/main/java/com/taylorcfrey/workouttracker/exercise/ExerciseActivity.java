package com.taylorcfrey.workouttracker.exercise;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.model.Exercise;

public class ExerciseActivity extends Activity implements ExerciseListFragment.OnFragmentInteractionListener{

    public static final String EXTRA_CREATE_EXERCISE = "com.taylorcfrey.workouttracker.exercise.CREATE_EXERCISE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        boolean createExtra = intent.getBooleanExtra(EXTRA_CREATE_EXERCISE, false);

        if (createExtra) {
            loadCreateExerciseFragment();
        } else {
            loadExerciseListFragment();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_create_exercise) {
            loadCreateExerciseFragment();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadExerciseListFragment() {
        ExerciseListFragment fragment = new ExerciseListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_exercise_container, fragment, "ExerciseListFragment");
        fragmentTransaction.commit();
    }

    private void loadCreateExerciseFragment() {
        CreateExerciseFragment fragment = new CreateExerciseFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_exercise_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void loadEditExerciseFragment(Exercise exercise) {
        EditExerciseFragment fragment = EditExerciseFragment.getInstance(exercise);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_exercise_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Exercise exercise) {
        loadEditExerciseFragment(exercise);
    }
}
