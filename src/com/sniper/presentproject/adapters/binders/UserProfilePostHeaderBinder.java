package com.sniper.presentproject.adapters.binders;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.adapters.DataBindAdapter;

public class UserProfilePostHeaderBinder extends DataBinder<UserProfilePostHeaderBinder.ViewHolder> {

    public UserProfilePostHeaderBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }

    @Override
    public ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_post_header_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder, int position) {
//        holder.userName.setText(R.drawable.bird);
//        Picasso.with(holder.mImageView.getContext())
//                .load(R.drawable.bird)
//                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView header;

        public ViewHolder(View view) {
            super(view);
            header = (TextView) view.findViewById(R.id.header);
        }
    }
}
