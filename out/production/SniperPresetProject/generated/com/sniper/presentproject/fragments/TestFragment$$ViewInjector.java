// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class TestFragment$$ViewInjector<T extends com.sniper.presentproject.fragments.TestFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361897, "field 'testLabel'");
    target.testLabel = finder.castView(view, 2131361897, "field 'testLabel'");
  }

  @Override public void reset(T target) {
    target.testLabel = null;
  }
}
