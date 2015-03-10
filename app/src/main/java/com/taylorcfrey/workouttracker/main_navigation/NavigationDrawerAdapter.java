package com.taylorcfrey.workouttracker.main_navigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taylorcfrey.workouttracker.R;

import java.util.List;

/**
 * Created by taylorfrey on 9/28/14.
 */
public class NavigationDrawerAdapter extends ArrayAdapter<NavigationItem> {

    private Context mContext;
    private List<NavigationItem> mNavigationItems;
    private int mResourceId;

    public NavigationDrawerAdapter(Context context, int resource) {
        super(context, resource);
    }

    public NavigationDrawerAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public NavigationDrawerAdapter(Context context, int resource, NavigationItem[] objects) {
        super(context, resource, objects);
    }

    public NavigationDrawerAdapter(Context context, int resource, int textViewResourceId, NavigationItem[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public NavigationDrawerAdapter(Context context, int resource, List<NavigationItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourceId = resource;
        this.mNavigationItems = objects;
    }

    public NavigationDrawerAdapter(Context context, int resource, int textViewResourceId, List<NavigationItem> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItemViewHolder mDrawerItemViewHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            mDrawerItemViewHolder = new DrawerItemViewHolder();

            view = inflater.inflate(mResourceId, parent, false);
            mDrawerItemViewHolder.title = (TextView) view
                    .findViewById(R.id.text_view_list_item_navigation_drawer);
            //TODO use DPI for calculation
            // Should use DPI to calculate the correct pixels so that this is correct on every screen
//            LinearLayout.LayoutParams iconLayoutParams = new LinearLayout.LayoutParams(75, 75);
            mDrawerItemViewHolder.icon = (ImageView) view.findViewById(R.id.icon_list_item_navigation_drawer);
//            mDrawerItemViewHolder.icon.setLayoutParams(iconLayoutParams);

            view.setTag(mDrawerItemViewHolder);

        } else {
            mDrawerItemViewHolder = (DrawerItemViewHolder) view.getTag();

        }

        NavigationItem navigationItem = (NavigationItem) this.mNavigationItems.get(position);

        mDrawerItemViewHolder.icon.setImageDrawable(view.getResources().getDrawable(
                navigationItem.getImageResourceId()));
        mDrawerItemViewHolder.title.setText(navigationItem.getTitle());

        return view;
    }

    // ViewHolder pattern
    private static class DrawerItemViewHolder {
        TextView title;
        ImageView icon;
    }
}
