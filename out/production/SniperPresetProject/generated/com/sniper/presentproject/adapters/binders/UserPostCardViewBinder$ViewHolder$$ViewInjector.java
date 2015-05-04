// Generated code from Butter Knife. Do not modify!
package com.sniper.presentproject.adapters.binders;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class UserPostCardViewBinder$ViewHolder$$ViewInjector<T extends com.sniper.presentproject.adapters.binders.UserPostCardViewBinder.ViewHolder> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361904, "field 'postUserName'");
    target.postUserName = finder.castView(view, 2131361904, "field 'postUserName'");
    view = finder.findRequiredView(source, 2131361901, "field 'postUserImage'");
    target.postUserImage = finder.castView(view, 2131361901, "field 'postUserImage'");
    view = finder.findRequiredView(source, 2131361902, "field 'threeDotMenu'");
    target.threeDotMenu = finder.castView(view, 2131361902, "field 'threeDotMenu'");
    view = finder.findRequiredView(source, 2131361905, "field 'postUserDate'");
    target.postUserDate = finder.castView(view, 2131361905, "field 'postUserDate'");
    view = finder.findRequiredView(source, 2131361906, "field 'postText'");
    target.postText = finder.castView(view, 2131361906, "field 'postText'");
    view = finder.findRequiredView(source, 2131361907, "field 'postImage'");
    target.postImage = finder.castView(view, 2131361907, "field 'postImage'");
    view = finder.findRequiredView(source, 2131361890, "field 'likes'");
    target.likes = finder.castView(view, 2131361890, "field 'likes'");
    view = finder.findRequiredView(source, 2131361891, "field 'comments'");
    target.comments = finder.castView(view, 2131361891, "field 'comments'");
    view = finder.findRequiredView(source, 2131361892, "field 'shares'");
    target.shares = finder.castView(view, 2131361892, "field 'shares'");
    view = finder.findRequiredView(source, 2131361893, "field 'descs'");
    target.descs = finder.castView(view, 2131361893, "field 'descs'");
  }

  @Override public void reset(T target) {
    target.postUserName = null;
    target.postUserImage = null;
    target.threeDotMenu = null;
    target.postUserDate = null;
    target.postText = null;
    target.postImage = null;
    target.likes = null;
    target.comments = null;
    target.shares = null;
    target.descs = null;
  }
}
