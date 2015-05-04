// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class HomeFragment$$ViewInjector<T extends com.sniper.presentproject.fragments.HomeFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361873, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131361873, "field 'recyclerView'");
  }

  @Override public void reset(T target) {
    target.recyclerView = null;
  }
}
