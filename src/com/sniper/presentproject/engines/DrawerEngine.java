package com.sniper.presentproject.engines;

import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.activities.BaseActivity;
import com.sniper.presentproject.adapters.NavDrawerAdapter;
import com.sniper.presentproject.models.NavDrawerModel;

import java.util.ArrayList;

public class DrawerEngine implements ListView.OnItemClickListener{
    private BaseActivity activity;
    private DrawerLayout drawerLayout;
    private FrameLayout menuLayout;
    private ListView drawerList;
    private NavDrawerAdapter slideMenuAdapter;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private int selectedDrawerItem;
    private ArrayList<NavDrawerModel> slideMenuItems = new ArrayList<NavDrawerModel>();

    public DrawerEngine(){}
    public DrawerEngine(BaseActivity activity, DrawerLayout drawerLayout, Toolbar toolbar, FrameLayout frameLayout, ListView drawerList){
        this.activity = activity;
        this.drawerLayout = drawerLayout;
        this.toolbar = toolbar;
        this.menuLayout = frameLayout;
        this.drawerList = drawerList;
        populateDrawerItems();
        createDrawerToggle();

    }

    private void createDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.action_bar_main_title, R.string.action_bar_main_title) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerStateChanged(int state) {
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void populateDrawerItems(){
        drawerList.setVerticalScrollBarEnabled(false);
//        drawerList.setDivider(this.getResources().getDrawable(android.R.color.transparent));
//        drawerList.setDividerHeight(0);
        String [] drawerLabels = activity.getResources().getStringArray(R.array.drawer_labels);
        TypedArray drawerIcons = activity.getResources().obtainTypedArray(R.array.drawer_icons);
        slideMenuItems = new ArrayList<NavDrawerModel>();

        for (int i = 0; i < drawerLabels.length;i++){
            slideMenuItems.add(new NavDrawerModel(drawerLabels[i], drawerIcons.getResourceId(i,-1)));
        }
        drawerIcons.recycle();

        slideMenuAdapter = new NavDrawerAdapter(activity.getApplicationContext(), R.layout.drawer_list_item, slideMenuItems);
        drawerList.setAdapter(slideMenuAdapter);
        drawerList.setOnItemClickListener(this);
        drawerLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                VideosCoordinator.hideAllMediaControllers(VideosCoordinator.instance().shownMediaControllers);
                return false;
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedDrawerItem = position;
            activity.displayView(position);
            drawerLayout.closeDrawer(menuLayout);
    }
    public ActionBarDrawerToggle getDrawerToggle() {
        return drawerToggle;
    }
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }
    public Toolbar getToolbar() {
        return toolbar;
    }
}
