package com.taylorcfrey.workouttracker.application;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.taylorcfrey.workouttracker.utils.TypefaceManager;
import com.taylorcfrey.workouttracker.utils.WorkoutTrackerApplication;

import junit.framework.Assert;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<WorkoutTrackerApplication> {

    public ApplicationTest() {
        super(WorkoutTrackerApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Basic test to ensure the application is created successfully.
     */
    public void testApplicationCreation() {
        createApplication();
    }

    /**
     * Test the creation of the Singleton for the TypefaceManager
     */
    public void testFontLoaded() {
        testApplicationCreation();
        Assert.assertNotNull(TypefaceManager.getInstance());
    }
}