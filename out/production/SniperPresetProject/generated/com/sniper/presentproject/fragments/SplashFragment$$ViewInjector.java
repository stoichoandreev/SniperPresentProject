// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SplashFragment$$ViewInjector<T extends com.sniper.presentproject.fragments.SplashFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361896, "field 'bottomContainer'");
    target.bottomContainer = finder.castView(view, 2131361896, "field 'bottomContainer'");
  }

  @Override public void reset(T target) {
    target.bottomContainer = null;
  }
}
