// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MainActivity$$ViewInjector<T extends com.sniper.presentproject.activities.MainActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361877, "field 'profileImage'");
    target.profileImage = finder.castView(view, 2131361877, "field 'profileImage'");
    view = finder.findRequiredView(source, 2131361875, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131361875, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131361874, "field 'drawerLayout'");
    target.drawerLayout = finder.castView(view, 2131361874, "field 'drawerLayout'");
    view = finder.findRequiredView(source, 2131361885, "field 'drawerList'");
    target.drawerList = finder.castView(view, 2131361885, "field 'drawerList'");
    view = finder.findRequiredView(source, 2131361879, "field 'scrimInsetsFrameLayout'");
    target.scrimInsetsFrameLayout = finder.castView(view, 2131361879, "field 'scrimInsetsFrameLayout'");
  }

  @Override public void reset(T target) {
    target.profileImage = null;
    target.toolbar = null;
    target.drawerLayout = null;
    target.drawerList = null;
    target.scrimInsetsFrameLayout = null;
  }
}
