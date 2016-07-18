package com.jkapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jkapp.R;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 侧滑菜单列表适配器
 * 
 * @author mao
 *
 */
public class SlidingMenuAdapter extends BaseAdapter {

	private Context mContext;
	
	private List<String> mList;
	
	public SlidingMenuAdapter(Context context) {
		mContext = context;
		init();
	}
	
	private void init() {
		mList = new ArrayList<String>();
		Resources res = mContext.getResources();
		mList.add(res.getString(R.string.personinfo));
		mList.add(res.getString(R.string.mypost));
		mList.add(res.getString(R.string.index_page));
		mList.add(res.getString(R.string.settings));
		mList.add(res.getString(R.string.exit));
	}
	
	@Override
	public int getCount() {
		if(mList != null) {
			return mList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.sliding_menu_lv_item, parent, false);
			holder = new ViewHolder();
			holder.sliding_menu_lv_item_icon = (ImageView) convertView.findViewById(R.id.sliding_menu_lv_item_icon);
			holder.sliding_menu_lv_item_text = (TextView) convertView.findViewById(R.id.sliding_menu_lv_item_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		setItemView(holder, (String) getItem(position));
		
		return convertView;
	}
	
	private void setItemView(ViewHolder holder, String s) {
		clearView(holder);
		
		Resources res = mContext.getResources();
		String personInfo = res.getString(R.string.personinfo);
		String myPost = res.getString(R.string.mypost);
		String index_page = res.getString(R.string.index_page);
		String settings = res.getString(R.string.settings);
		String exit = res.getString(R.string.exit);
		int resId = R.drawable.ic_launcher;
		
		String text = s;
		if(index_page.equals(s)) resId = R.drawable.home;
		if(settings.equals(s)) resId = R.drawable.menu_setting;
		if(personInfo.equals(s)) resId = R.drawable.personinfo;
		if(myPost.equals(s)) resId = R.drawable.mypost;
		if(exit.equals(s)) resId = R.drawable.login_selector;
		
	    holder.sliding_menu_lv_item_icon.setImageResource(resId);
		holder.sliding_menu_lv_item_text.setText(text);
		
	}
	
	private void clearView(ViewHolder holder) {
		holder.sliding_menu_lv_item_icon.setImageBitmap(null);
		holder.sliding_menu_lv_item_text.setText("");
	}
	
	private static class ViewHolder {
		
		private ImageView sliding_menu_lv_item_icon;
		private TextView sliding_menu_lv_item_text; 
	}
}
