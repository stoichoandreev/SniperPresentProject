// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ParallaxScrollFragment$$ViewInjector<T extends com.sniper.presentproject.fragments.ParallaxScrollFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361832, "field 'imageView'");
    target.imageView = finder.castView(view, 2131361832, "field 'imageView'");
    view = finder.findRequiredView(source, 2131361887, "field 'scrollView'");
    target.scrollView = finder.castView(view, 2131361887, "field 'scrollView'");
  }

  @Override public void reset(T target) {
    target.imageView = null;
    target.scrollView = null;
  }
}
