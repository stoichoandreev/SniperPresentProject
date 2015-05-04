package com.sniper.presentproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.custom.PathView;


public class SplashFragment extends BaseFragment {

    @InjectView(R.id.logo_container) RelativeLayout bottomContainer;
    private PathView logo;

    public SplashFragment(){}

    public static SplashFragment newInstance(Bundle bundle) {
        SplashFragment f = new SplashFragment();
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayoutRecourse(R.layout.splash_layout);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this,getRootView());
        initUI();
        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initUI(){
        logo = new PathView(mActivity);
        bottomContainer.addView(logo);
        logo.setEventListener(new PathView.PathViewAnimationListener() {
            @Override
            public void onPathViewAnimationStart() {
            }
            @Override
            public void onPathViewAnimationFinish() {
            }
            @Override
            public void onPathViewAnimationCancel() {
            }
            @Override
            public void onPathViewAnimationRepeat() {
            }
        });
    }
}
