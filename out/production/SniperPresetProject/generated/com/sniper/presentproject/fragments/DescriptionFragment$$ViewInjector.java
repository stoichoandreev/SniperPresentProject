// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class DescriptionFragment$$ViewInjector<T extends com.sniper.presentproject.fragments.DescriptionFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361871, "field 'contButton'");
    target.contButton = finder.castView(view, 2131361871, "field 'contButton'");
    view = finder.findRequiredView(source, 2131361872, "field 'userLevel'");
    target.userLevel = finder.castView(view, 2131361872, "field 'userLevel'");
  }

  @Override public void reset(T target) {
    target.contButton = null;
    target.userLevel = null;
  }
}
