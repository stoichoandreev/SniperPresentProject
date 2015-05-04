package com.sniper.presentproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.sniper.presentproject.R;
import com.sniper.presentproject.fragments.DescriptionFragment;
import com.sniper.presentproject.fragments.SplashFragment;

public class SplashActivity extends BaseActivity {

    private static int SPLASH_DELAY = 7000;
    private Handler handler;
    private Runnable applicationRunnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.setLayoutResource(R.layout.splash_activity_layout);
        super.onCreate(savedInstanceState);
        handler = new Handler();
        applicationRunnable = getApplicationRunnable();

        LinearLayout content = (LinearLayout) findViewById(R.id.registration_activity_container);
        FrameLayout fragmentCont = (FrameLayout) content.getChildAt(0);
        setFragmentContainerId(fragmentCont.getId());
        startSplashScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void startSplashScreen() {
        Bundle bundle = new Bundle();
        SplashFragment splashFragment = SplashFragment.newInstance(bundle);
        addFragment(splashFragment, false);
        startApplication();
    }
    private void startDescriptionScreen() {
        Bundle bundle = new Bundle();
        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(bundle);
        addFragment(descriptionFragment, true);
    }
    private Runnable getApplicationRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                if(mApplication.getApplicationSession().isSessionOpen()) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    startDescriptionScreen();
                }
            }
        };
    }
    private void startApplication(){
        handler.postDelayed(applicationRunnable, SPLASH_DELAY);
    }
}
