// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.adapters.binders;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class UserProfileImageBinder$ViewHolder$$ViewInjector<T extends com.sniper.presentproject.adapters.binders.UserProfileImageBinder.ViewHolder> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361898, "field 'userName'");
    target.userName = finder.castView(view, 2131361898, "field 'userName'");
    view = finder.findRequiredView(source, 2131361899, "field 'circleUserLevel'");
    target.circleUserLevel = finder.castView(view, 2131361899, "field 'circleUserLevel'");
  }

  @Override public void reset(T target) {
    target.userName = null;
    target.circleUserLevel = null;
  }
}
