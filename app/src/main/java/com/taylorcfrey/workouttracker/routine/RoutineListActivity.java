package com.taylorcfrey.workouttracker.routine;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.exercise.CreateExerciseFragment;
import com.taylorcfrey.workouttracker.exercise.ExerciseActivity;

/**
 * An activity representing a list of Routines. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RoutineDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link com.taylorcfrey.workouttracker.routine.RoutineListFragment} and the item details
 * (if present) is a {@link com.taylorcfrey.workouttracker.routine.RoutineDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link com.taylorcfrey.workouttracker.routine.RoutineListFragment.RoutineListListener} interface
 * to listen for item selections.
 */
public class RoutineListActivity extends Activity
        implements RoutineListFragment.RoutineListListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        loadRoutineListFragment();

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (findViewById(R.id.routine_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((RoutineListFragment) getFragmentManager()
                    .findFragmentById(R.id.routine_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.routine, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else if (id == R.id.action_create_exercise) {
            loadCreateExerciseActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback method from {@link com.taylorcfrey.workouttracker.routine.RoutineListFragment.RoutineListListener}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(RoutineDetailFragment.ROUTINE_NAME, id);
            RoutineDetailFragment fragment = new RoutineDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.routine_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, RoutineDetailActivity.class);
            detailIntent.putExtra(RoutineDetailFragment.ROUTINE_NAME, id);
            startActivity(detailIntent);
        }
    }

    public void loadRoutineListFragment() {
        RoutineListFragment routineListFragment = new RoutineListFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.activity_routine, routineListFragment);
        transaction.commit();
    }

    public void loadCreateExerciseActivity() {
        Intent createExerciseIntent = new Intent(this, ExerciseActivity.class);
        createExerciseIntent.putExtra(ExerciseActivity.EXTRA_CREATE_EXERCISE, true);
        startActivity(createExerciseIntent);
    }
}
