package com.sniper.presentproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sniper.presentproject.PresentApplication;
import com.sniper.presentproject.activities.BaseActivity;

public class BaseFragment extends Fragment {
    private View rootView;
    private int mLayoutRecourse;
    protected BaseActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(mLayoutRecourse, container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.hideSoftKeyBoard();
    }

    public void setLayoutRecourse(int res) {
        mLayoutRecourse = res;
    }

    public View getRootView() {
        return rootView;
    }
    public PresentApplication getPresentApplication(){return (PresentApplication) mActivity.getApplication();}
}