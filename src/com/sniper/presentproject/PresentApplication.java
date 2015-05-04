package com.sniper.presentproject;

import android.app.Application;
import com.sniper.presentproject.session.Session;

/**
 * Created by sniper on 1/25/15.
 */
public class PresentApplication extends Application {
    private Session session;
    public float density;//we always need density in the project
    @Override
    public void onCreate() {
        super.onCreate();
        session = new Session();// it just a example. On real app it will be much complex
        density = getResources().getDisplayMetrics().density;
    }
    public Session getApplicationSession(){return session;}
}
