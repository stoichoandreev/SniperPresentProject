package com.sniper.presentproject.activities;
/**
 * Created by sniper on 1/25/15.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.sniper.presentproject.PresentApplication;
import com.sniper.presentproject.R;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity  {

    protected ProgressDialog progressDialog;
    protected PresentApplication mApplication;
    protected boolean mIsActivityStartForFirstTime;
    private int layoutResource;
    private int fragmentContainerId;
    protected ActionDrawableState mToolbarIconState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResource);
        mIsActivityStartForFirstTime = true;
        mApplication = (PresentApplication) getApplication();
    }

    public void addFragment(Fragment fragment,boolean withAnimation) {
        FragmentTransaction t = getFragmentManager().beginTransaction();
        if(withAnimation) t.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit, R.anim.fragment_slide_right_enter, R.anim.fragment_slide_right_exit);

        String fragmentClassName = ((Object) fragment).getClass().getSimpleName();
        t.replace(fragmentContainerId, fragment, fragmentClassName);
        t.addToBackStack(fragmentClassName);
        t.commit();
        if(getFragmentManager().getBackStackEntryCount() > 0){
            mToolbarIconState = ActionDrawableState.ARROW;
        }
    }

    public void showSoftKeyBoard(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(null, 0);
        }
    }

    public void shouldDisplayHomeUp() {
    }

    @Override
    public boolean onNavigateUp() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            onBackPressed();
            return true;
        } else return super.onNavigateUp();
    }
    public void clearBackStack() {
//        returnToMainView(TodayInformationFragment.class.getSimpleName());
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                drawerToggle.setDrawerIndicatorEnabled(true);
//                selectedDrawerItem = 0;
//                getActionBar().setTitle(Html.fromHtml(slideMenuItems.get(0).getTitle()));
//                getSlideMenuAdapter().setSelectedItem(0);
//                getSlideMenuAdapter().getItem(selectedDrawerItem).setSelected(false);
//                getSlideMenuAdapter().notifyDataSetChanged();
//            }
//        });
    }
    public void returnToMainView() {
        getFragmentManager().popBackStack(1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        getFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        if (getFragmentManager().getBackStackEntryCount() > 2) {
//            FragmentManager.BackStackEntry entry = getFragmentManager().getBackStackEntryAt(1);
//            if (entry.getName().equals(name)) {
////                drawerToggle.setDrawerIndicatorEnabled(true);
////                backPressed = true;
//            }
//        }
    }
    protected void setLayoutResource(int resource){
        this.layoutResource = resource;
    }

    protected enum ActionDrawableState{
        BURGER, ARROW
    }
    public void displayView(final int position){}
    public ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        return progressDialog;
    }
    protected void setFragmentContainerId(int id){fragmentContainerId = id;}

}