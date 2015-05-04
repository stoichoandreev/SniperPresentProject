package com.sniper.presentproject.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.adapters.PostRecyclerViewAdapter;
import com.sniper.presentproject.adapters.SimpleSectionedRecyclerViewAdapter;
import com.sniper.presentproject.models.UserPostModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    @InjectView(R.id.card_recycler_view)  RecyclerView recyclerView;
    private PostRecyclerViewAdapter adapter;
    private ArrayList<UserPostModel> allPosts;

    public HomeFragment(){}
    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment f = new HomeFragment();
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayoutRecourse(R.layout.home_fragment_layout);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, getRootView());
        getAllPosts();
        //Your RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));

        //Your RecyclerView.Adapter
        adapter = new PostRecyclerViewAdapter();
        adapter.setUserPostCardData(allPosts);


        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        //Sections
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"Section 1"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(1,"Friends Posts"));
//        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(5,"Section 2"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(mActivity,R.layout.user_post_header_layout,R.id.header,adapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        recyclerView.setAdapter(mSectionedAdapter);

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    private void getAllPosts(){
        //if we haven't a list with object create it
        if(allPosts == null) {
            allPosts = new ArrayList<UserPostModel>();

            UserPostModel mod1 = new UserPostModel();
            mod1.setUserName("Roger Federer");
            mod1.setPostText("Post about sport event number one ....");
            mod1.setUserLevel(9);
            mod1.setLikesNumber(123);
            mod1.setCommentsNumber(24);
            mod1.setSharesNumber(15);

            UserPostModel mod2 = new UserPostModel();
            mod2.setUserName("John Smith");
            mod2.setPostText("Post about sport event number two and some more details");
            mod2.setUserLevel(2);
            mod2.setLikesNumber(56);
            mod2.setCommentsNumber(33);
            mod2.setSharesNumber(10);
            allPosts.add(mod1);
            allPosts.add(mod2);
        }
    }
}
