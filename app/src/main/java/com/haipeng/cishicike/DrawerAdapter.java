package com.haipeng.cishicike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haipeng.util.myInterface.ToggleInter;

public class DrawerAdapter extends BaseAdapter {

	Context mContext;
	List<String> mList = new ArrayList<String>();
    ToggleInter mToggleInter;
   static Map<Integer,String> jugeList = new HashMap<Integer,String>();
	public DrawerAdapter(Context context, List<String> list,ToggleInter toggleInter) {
		mList = list;
		mContext = context;
        mToggleInter = toggleInter;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
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
            String mStr = mList.get(position-1);
            String str1 = mStr.substring(mStr.indexOf("[")+1,mStr.indexOf("]"));
            jugeList.put(position,str1);
            String str2 = mStr.substring(mStr.indexOf("]")+1,mStr.length());
			viewHolder.mTV.setText(str2);
		}
        convertView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToggleInter.OnClickToggle(jugeList.get(position));
            }
        });
		return convertView;
	}

	public class ViewHolder {
		TextView mTV;
	}

}
