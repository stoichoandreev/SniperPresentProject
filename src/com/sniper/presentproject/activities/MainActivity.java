package com.sniper.presentproject.activities;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.constants.Preferences;
import com.sniper.presentproject.custom.ScrimInsetsFrameLayout;
import com.sniper.presentproject.engines.DrawerEngine;
import com.sniper.presentproject.fragments.*;

public class MainActivity extends BaseActivity implements View.OnClickListener, FragmentManager.OnBackStackChangedListener{

    private static final int VIEW_WITHOUT_ID = -1;
    private DrawerEngine drawerEngine;
    @InjectView(R.id.profile_toolbar_image) ImageView profileImage;
    @InjectView(R.id.bar) Toolbar toolbar;
    @InjectView(R.id.drawerLayout) DrawerLayout drawerLayout;
    @InjectView(R.id.drawer_list_cont) ListView drawerList;
    @InjectView(R.id.scrimInsetsFrameLayout) ScrimInsetsFrameLayout scrimInsetsFrameLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.setLayoutResource(R.layout.main);
        super.onCreate(savedInstanceState);
        mToolbarIconState = ActionDrawableState.BURGER;
        getFragmentManager().addOnBackStackChangedListener(this);
        ButterKnife.inject(this);
        initUI();
        drawerEngine.getToolbar().setNavigationOnClickListener(this);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerEngine.getDrawerToggle().syncState();
        drawerEngine.getToolbar().setNavigationIcon(getResources().getDrawable(R.drawable.drawer_icon));
    }
    //
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerEngine.getDrawerToggle().onConfigurationChanged(newConfig);
    }
    @Override
    public void onBackPressed() {
        if(drawerEngine.getDrawerLayout().isDrawerOpen(Gravity.START | Gravity.LEFT)){
            drawerEngine.getDrawerLayout().closeDrawers();
            return;
        }
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStackImmediate();
        } else {
            getFragmentManager().popBackStackImmediate();
            super.onBackPressed();
        }
        if(getFragmentManager().getBackStackEntryCount() == 1) {
            mToolbarIconState = ActionDrawableState.BURGER;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case VIEW_WITHOUT_ID:
                if(mToolbarIconState == ActionDrawableState.ARROW){
                    onNavigateUp();
                }else if(mToolbarIconState == ActionDrawableState.BURGER){
                    drawerEngine.getDrawerLayout().openDrawer(Gravity.LEFT);
                }
                break;
        }
    }
    private void initUI() {
        if (mIsActivityStartForFirstTime) {
            mIsActivityStartForFirstTime = false;
            setFragmentContainerId(R.id.main_activity_fragment_container);
            drawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.white_color_with_alpha_40));

            drawerEngine = new DrawerEngine(this,drawerLayout,toolbar,scrimInsetsFrameLayout,drawerList);
            super.setSupportActionBar(toolbar);
            loadStartScreen();
        }
    }
    private void loadStartScreen(){
        Bundle bundle = new Bundle();
        HomeFragment startFragment = HomeFragment.newInstance(bundle);
        addFragment(startFragment, false);
    }
    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }
    @Override
    public void displayView(final int pos) {
        BaseFragment fragment = null;
        Bundle bundle = new Bundle();
        switch (pos){
            case Preferences.DISPLAY_MENU_ONE:
                fragment = ObserverScrollFragment.newInstance(bundle);
                break;
            case Preferences.DISPLAY_MENU_TWO:
                fragment = ParallaxScrollFragment.newInstance(bundle);
                break;
            case Preferences.DISPLAY_MENU_THREE:
                bundle.putString(Preferences.TEST_LABEL,"Menu Three Fragment");
                fragment = TestFragment.newInstance(bundle);
                break;
            case Preferences.DISPLAY_MENU_FOUR:
//          TODO make logout request and reset application token from SharedPreferences
                setResult(RESULT_OK);
                finish();
                break;
            default:
                bundle.putString(Preferences.TEST_LABEL,"Default Test Fragment");
                fragment = TestFragment.newInstance(bundle);
        }

        if(fragment !=null) {
            addFragment(fragment, true);
        }
    } // End of DisplayView

    public void shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        boolean canBack = getFragmentManager().getBackStackEntryCount() > 1;
        mToolbarIconState = (canBack) ? mToolbarIconState = ActionDrawableState.ARROW : ActionDrawableState.BURGER;
        drawerEngine.getToolbar().setNavigationIcon((canBack) ?
                getResources().getDrawable(R.drawable.ic_arrow_back_black) :
                getResources().getDrawable(R.drawable.drawer_icon));
        drawerEngine.getDrawerLayout().setDrawerLockMode((canBack)?
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED :
                DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    public DrawerEngine getDrawerEngine(){return drawerEngine;}

    //we use this method only for training to show and hide toolbar in special cases
    public void setToolbarVisibility(int visibility){
        getDrawerEngine().getToolbar().setVisibility(visibility);
        profileImage.setVisibility(visibility);
    }
}
