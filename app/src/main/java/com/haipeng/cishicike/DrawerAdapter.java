package com.haipeng.cishicike;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {

	Context mContext;
	List<String> mList = new ArrayList<String>();

	public DrawerAdapter(Context context, List<String> list) {
		mList = list;
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (position == 0) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.personal_info, null);
		} else {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_actionbat_drawer_toggle, null);
				viewHolder = new ViewHolder();
				viewHolder.mTV = (TextView) convertView
						.findViewById(R.id.drawer_text);
				convertView.setTag(viewHolder);

			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.mTV.setText(mList.get(position - 1));
		}
		return convertView;
	}

	public class ViewHolder {
		TextView mTV;
	}

}
