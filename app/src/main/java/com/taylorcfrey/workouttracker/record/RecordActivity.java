package com.taylorcfrey.workouttracker.record;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.taylorcfrey.workouttracker.R;

public class RecordActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        if (savedInstanceState == null) {
            loadResultFragment();
        }
    }

    public void loadResultFragment() {
        RecordFragment fragment = new RecordFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_record_container, fragment, "RecordFragment");
        fragmentTransaction.commit();
    }

}
