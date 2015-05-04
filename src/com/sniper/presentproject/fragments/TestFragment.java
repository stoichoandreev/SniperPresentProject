package com.sniper.presentproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.constants.Preferences;



public class TestFragment extends BaseFragment {
    @InjectView(R.id.test_label) TextView testLabel;

    public TestFragment(){}
    public static TestFragment newInstance(Bundle bundle) {
        TestFragment f = new TestFragment();
        f.setLayoutRecourse(R.layout.test_fragment_layout);
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this,getRootView());
        testLabel.setText((getArguments().containsKey(Preferences.TEST_LABEL)) ?
                getArguments().getString(Preferences.TEST_LABEL) :
                "Just a test label");
        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
