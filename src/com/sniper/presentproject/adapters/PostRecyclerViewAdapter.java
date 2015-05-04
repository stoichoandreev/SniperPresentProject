package com.sniper.presentproject.adapters;

// Source https://github.com/yqritc/RecyclerView-MultipleViewTypesAdapter


import com.sniper.presentproject.adapters.binders.UserPostCardViewBinder;
import com.sniper.presentproject.adapters.binders.UserProfileImageBinder;
import com.sniper.presentproject.models.UserPostModel;

import java.util.List;

/**
 * Created by yqritc on 2015/04/20.
 */
public class PostRecyclerViewAdapter extends EnumListBindAdapter<PostRecyclerViewAdapter.PostAdapterViewType> {

    enum PostAdapterViewType {
        USER_PROFILE_TYPE, POST_TYPE
//        USER_PROFILE_TYPE, POST_HEADER_TYPE, POST_TYPE
    }

    public PostRecyclerViewAdapter() {
        addAllBinder(new UserProfileImageBinder(this),
//                new UserProfilePostHeaderBinder(this),
                new UserPostCardViewBinder(this));
    }

    public void setUserPostCardData(List<UserPostModel> dataSet) {
        ((UserPostCardViewBinder) getDataBinder(PostAdapterViewType.POST_TYPE)).addAll(dataSet);
    }
}
