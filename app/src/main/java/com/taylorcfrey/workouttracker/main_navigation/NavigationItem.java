package com.taylorcfrey.workouttracker.main_navigation;

/**
 * Created by taylorfrey on 9/28/14.
 */
public class NavigationItem {

    private String mTitle;
    private int mImageResourceId;

    public NavigationItem(String title, int resId) {
        super();
        mTitle = title;
        mImageResourceId = resId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        this.mTitle = mTitle;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int ImageResourceId) {
        this.mImageResourceId = ImageResourceId;
    }
}
