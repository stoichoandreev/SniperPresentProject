package com.sniper.presentproject.adapters.binders;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.adapters.DataBindAdapter;
import com.sniper.presentproject.custom.CircleUserLevel;
import com.sniper.presentproject.models.UserPostModel;

import java.util.ArrayList;
import java.util.List;


public class UserPostCardViewBinder extends DataBinder<UserPostCardViewBinder.ViewHolder> {
    private List<UserPostModel> mDataSet = new ArrayList<UserPostModel>();
    public UserPostCardViewBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_post_card_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
        UserPostModel model = mDataSet.get(position);
        holder.postUserName.setText(model.getUserName());
        holder.postUserDate.setText("a week ago");
        holder.postText.setText(model.getPostText());
        holder.postUserImage.setUserLevel(model.getUserLevel(), true);
        holder.likes.setText(Integer.toString(model.getLikesNumber()));
        holder.comments.setText(Integer.toString(model.getCommentsNumber()));
        holder.shares.setText(Integer.toString(model.getSharesNumber()));
        holder.descs.setText("15");
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void addAll(List<UserPostModel> dataSet) {
        mDataSet.addAll(dataSet);
        notifyBinderDataSetChanged();
    }

    public void clear() {
        mDataSet.clear();
        notifyBinderDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.post_user_name) TextView postUserName;
        @InjectView(R.id.post_user_image) CircleUserLevel postUserImage;
        @InjectView(R.id.three_dot_menu) ImageView threeDotMenu;
        @InjectView(R.id.post_user_date) TextView postUserDate;
        @InjectView(R.id.post_text) TextView postText;
        @InjectView(R.id.post_image) ImageView postImage;
        @InjectView(R.id.post_bar_likes) TextView likes;
        @InjectView(R.id.post_bar_comments) TextView comments;
        @InjectView(R.id.post_bar_shares) TextView shares;
        @InjectView(R.id.post_bar_descs) TextView descs;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
