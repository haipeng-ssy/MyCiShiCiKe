package com.haipeng.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haipeng.cishicike.R;
import com.haipeng.entity.MyChat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/15.
 */
public class ChatAdapter extends BaseAdapter {
    List<MyChat> mList;
    Activity mActivity;
    public ChatAdapter(Activity activity,List<MyChat> list){
           mList = new ArrayList<MyChat>();
           mList = list;
           mActivity = activity;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView =LayoutInflater.from(mActivity).inflate(R.layout.item_chat,null);
            viewHolder.account = (TextView) convertView.findViewById(R.id.item_chat_account);
            viewHolder.content = (TextView) convertView.findViewById(R.id.item_chat_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.account.setText(mList.get(position).getAccount()+": ");
        viewHolder.content.setText(mList.get(position).getContent());

        return convertView;
    }
    private class ViewHolder{
        TextView account;
        TextView content;
    }
}
