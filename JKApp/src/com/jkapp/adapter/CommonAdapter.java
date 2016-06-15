package com.jkapp.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CommonAdapter<T> extends BaseAdapter{

	private List<T> mList;
	private OnCommonAdapterListener mListener;
	
	@Override
	public int getCount() {
		return mList != null ? mList.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(mListener != null) {
			return mListener.getView(position, convertView, parent);
		}
		return null;
	}
	
	public OnCommonAdapterListener getOnCommonAdapterListener() {
		return mListener;
	}

	public void setmListener(OnCommonAdapterListener l) {
		this.mListener = l;
	}


	public interface OnCommonAdapterListener {
		
		View getView(int position, View convertView, ViewGroup parent);
	}
}
