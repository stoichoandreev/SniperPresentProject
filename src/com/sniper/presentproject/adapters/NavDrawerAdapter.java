package com.sniper.presentproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sniper.presentproject.R;
import com.sniper.presentproject.models.NavDrawerModel;

import java.util.ArrayList;

public class NavDrawerAdapter extends ArrayAdapter<NavDrawerModel> {
    private int selectedItem = -1;
    private Context context;
    public NavDrawerAdapter(Context context, int textViewResourceId, ArrayList<NavDrawerModel> list){
        super(context, textViewResourceId, list);
        this.context = context;
    }
    static class ViewHolder{
        ImageView icon;
        TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final NavDrawerModel item = getItem(position);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
            holder = new ViewHolder();
            holder.icon = (ImageView)convertView.findViewById(R.id.icon);
            holder.title = (TextView)convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
//        holder.icon.setImageDrawable(context.getResources().getDrawable(item.getIcon()));
        holder.title.setText(item.getTitle());
        holder.icon.setImageResource(item.getIcon());
        //mark selected slide menu item
        if(selectedItem > (-1)){
            //convertView.setBackgroundColor((position == selectedItem) ? getContext().getResources().getColor(R.color.light_grey):Color.WHITE);
        	convertView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        	holder.title.setTextColor(((position == selectedItem) ? getContext().getResources().getColor(android.R.color.white):context.getResources().getColor(R.color.white_color_with_alpha_40)));
        }

        return convertView;
    }
    public void setSelectedItem(int setSelectedItem) {
        this.selectedItem = setSelectedItem;
    }
}