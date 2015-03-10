package com.taylorcfrey.workouttracker.main_navigation;

import android.app.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.taylorcfrey.workouttracker.R;
import com.taylorcfrey.workouttracker.exercise.ExerciseActivity;
import com.taylorcfrey.workouttracker.record.RecordActivity;
import com.taylorcfrey.workouttracker.routine.RoutineListActivity;


public class MainNavigationActivity extends Activity
        implements MainNavigationDrawerFragment.NavigationDrawerCallbacks {

    private final int POSITION_ROUTINES = 0;
    private final int POSITION_RESULTS = 1;
    private final int POSITION_EXERCISES = 2;


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private MainNavigationDrawerFragment mMainNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mMainNavigationDrawerFragment = (MainNavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mMainNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        switch (position) {
            case POSITION_ROUTINES:
                Intent routineIntent = new Intent(this, RoutineListActivity.class);
                startActivity(routineIntent);
                break;
            case POSITION_RESULTS:
                Intent recordIntent = new Intent(this, RecordActivity.class);
                startActivity(recordIntent);
                break;
            case POSITION_EXERCISES:
                Intent exerciseIntent = new Intent(this, ExerciseActivity.class);
                startActivity(exerciseIntent);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mMainNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
