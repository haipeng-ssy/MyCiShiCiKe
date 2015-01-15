package com.haipeng.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.haipeng.cishicike.R;
import com.haipeng.entity.Friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/1/14.
 */
public class FriendsAdapter extends BaseAdapter {
    Activity activity;
    List<Friend> list_friend;
    public FriendsAdapter(Activity mActivity,List<Friend> list){
        activity = mActivity;
        list_friend = new ArrayList<Friend>();
        list_friend = list;
    }
    @Override
    public int getCount() {
        return list_friend.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<Friend> getListFriends(){
        return list_friend;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_friend,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.friend_avator);
            viewHolder.textView  = (TextView)  convertView.findViewById(R.id.friend_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setBackgroundColor(Color.BLUE);
        viewHolder.textView.setText(list_friend.get(position).getFriendName());
        return convertView;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView  textView;
    }

}
