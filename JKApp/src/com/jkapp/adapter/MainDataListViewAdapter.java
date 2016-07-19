package com.jkapp.adapter;

import java.util.List;

import com.jkapp.R;
import com.jkapp.bean.MainData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainDataListViewAdapter extends BaseAdapter {
	private List<MainData> mList;
	private Context mContext;
	
	public MainDataListViewAdapter(Context context, List<MainData> list) {
		// TODO Auto-generated constructor stub
		mList = list;
		mContext = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final MainData mainData = mList.get(position);
		ViewHolder viewHolder;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.main_data_lv_item, null);
		    viewHolder = new ViewHolder();
			viewHolder.questionTitle = (TextView) convertView.findViewById(R.id.questionTitle);
			viewHolder.answerDetail = (TextView) convertView.findViewById(R.id.answerDetail);
//			viewHolder.commentNumbers = (TextView) convertView.findViewById(R.id.commentNumbers);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.questionTitle.setText(mainData.getTitle());
		viewHolder.answerDetail.setText(mainData.getMethod());
//		viewHolder.commentNumbers.setText(mainData.getCommentNumbers());
		
		return convertView;
		
	}
	
	class ViewHolder {
		TextView questionTitle;
		TextView answerDetail;
//		TextView commentNumbers;
	}
}
