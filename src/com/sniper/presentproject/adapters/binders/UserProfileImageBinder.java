package com.sniper.presentproject.adapters.binders;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.adapters.DataBindAdapter;
import com.sniper.presentproject.custom.CircleUserLevel;

public class UserProfileImageBinder extends DataBinder<UserProfileImageBinder.ViewHolder> {

    public UserProfileImageBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_image_frame_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
//        holder.userName.setText(R.drawable.bird);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.user_name) TextView userName;
        @InjectView(R.id.big_image_circle_user_level) CircleUserLevel circleUserLevel;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
