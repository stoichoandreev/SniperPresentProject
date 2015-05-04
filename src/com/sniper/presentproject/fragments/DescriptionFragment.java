package com.sniper.presentproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.activities.MainActivity;
import com.sniper.presentproject.custom.CircleUserLevel;


/**
 * Created by sniper on 03/29/15.
 */
public class DescriptionFragment extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.continue_button) Button contButton;
    @InjectView(R.id.circle_user_level) CircleUserLevel userLevel;

    public DescriptionFragment(){}
    public static DescriptionFragment newInstance(Bundle bundle) {
        DescriptionFragment f = new DescriptionFragment();
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayoutRecourse(R.layout.description_fragment_layout);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, getRootView());

        return getRootView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userLevel.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circle_user_level:
                Intent mainActivity = new Intent(mActivity, MainActivity.class);
                startActivity(mainActivity);
                mActivity.finish();
                break;
        }
    }
}
