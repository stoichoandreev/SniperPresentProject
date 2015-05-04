package com.sniper.presentproject.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.activities.MainActivity;
import com.sniper.presentproject.adapters.ObservableScrollRecyclerAdapter;
import com.sniper.presentproject.observable.ObservableRecyclerView;
import com.sniper.presentproject.observable.ObservableScrollViewCallbacks;
import com.sniper.presentproject.observable.ScrollState;

import java.util.ArrayList;


public class ObserverScrollFragment extends BaseFragment implements ObservableScrollViewCallbacks {
    @InjectView(R.id.recycler) ObservableRecyclerView recyclerView;

    private static final int NUM_OF_ITEMS = 100;
    private int toolbarHeight;

    public ObserverScrollFragment(){}
    public static ObserverScrollFragment newInstance(Bundle bundle) {
        ObserverScrollFragment f = new ObserverScrollFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[] { android.R.attr.actionBarSize };
        int indexOfAttrTextSize = 0;
        TypedArray a = mActivity.obtainStyledAttributes(typedValue.data, textSizeAttr);
        toolbarHeight = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayoutRecourse(R.layout.observer_scroll_fragment_layout);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, getRootView());

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setHasFixedSize(true);
        recyclerView.setScrollViewCallbacks(this);
        setDummyData(recyclerView);
        return getRootView();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        boolean isToolBarVisible = ((MainActivity)mActivity).getDrawerEngine().getToolbar().isShown();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        if (scrollState == ScrollState.UP) {
            if (isToolBarVisible) {
                ((MainActivity)mActivity).setToolbarVisibility(View.GONE);
                params.setMargins(0, 0, 0, 0);
                recyclerView.setLayoutParams(params);
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!isToolBarVisible) {
                ((MainActivity)mActivity).setToolbarVisibility(View.VISIBLE);
                params.setMargins(0, toolbarHeight, 0, 0);
                recyclerView.setLayoutParams(params);
            }
        }
    }
    protected void setDummyData(RecyclerView recyclerView) {
        setDummyData(recyclerView, NUM_OF_ITEMS);
    }
    protected void setDummyData(RecyclerView recyclerView, int num) {
        recyclerView.setAdapter(new ObservableScrollRecyclerAdapter(mActivity, getDummyData(num)));
    }
    public static ArrayList<String> getDummyData(int num) {
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 1; i <= num; i++) {
            items.add("Just example Item " + i);
        }
        return items;
    }
}
