package com.sniper.presentproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nineoldandroids.view.ViewHelper;
import com.sniper.presentproject.R;
import com.sniper.presentproject.activities.MainActivity;
import com.sniper.presentproject.observable.ObservableScrollView;
import com.sniper.presentproject.observable.ObservableScrollViewCallbacks;
import com.sniper.presentproject.observable.ScrollState;
import com.sniper.presentproject.observable.ScrollUtils;

/**
 * Created by sniper on 5/4/15.
 */
public class ParallaxScrollFragment extends BaseFragment implements ObservableScrollViewCallbacks {
    private int mParallaxImageHeight;

    @InjectView(R.id.image) ImageView imageView;
    @InjectView(R.id.scroll) ObservableScrollView scrollView;

    public ParallaxScrollFragment(){}
    public static ParallaxScrollFragment newInstance(Bundle bundle) {
        ParallaxScrollFragment f = new ParallaxScrollFragment();
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayoutRecourse(R.layout.parallax_scroll_fragment_layout);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, getRootView());
        ((MainActivity)mActivity).getDrawerEngine().getToolbar().setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.toolbar_color)));

        scrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);
        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity)mActivity).getDrawerEngine().getToolbar().setBackgroundColor(getResources().getColor(R.color.toolbar_color));
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.toolbar_color);
        float alpha = 1 - (float) Math.max(0, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        ((MainActivity)mActivity).getDrawerEngine().getToolbar().setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(imageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }
}
