package com.jkapp.adapter;

import java.util.List;

import com.jkapp.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
public class CommentAdapter extends BaseAdapter {
    private List<String> mData;
    private LayoutInflater mInflater;
   
    public CommentAdapter(Context context,List<String>data){
    	this.mData=data;
    	
    	mInflater=LayoutInflater.from(context);
    	
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			
			convertView=mInflater.inflate(R.layout.common_listview_item,null);
			holder.img=(ImageView)convertView.findViewById(R.drawable.user_default2);
			holder.username=(TextView)convertView.findViewById(R.id.username);
			holder.comment_content=(TextView)convertView.findViewById(R.id.comment_content);
			holder.creat_time=(TextView)convertView.findViewById(R.id.creat_time);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
			
		}
		holder.img.setBackgroundResource(R.drawable.user_default2);;
		holder.username.setText(mData.get(position));
		holder.comment_content.setText(mData.get(position));
		holder.creat_time.setText(mData.get(position));
		return convertView;
	}
    public final class ViewHolder{
    	public ImageView img;
    	public TextView username;
    	public TextView comment_content;
    	public TextView creat_time;
    }
}
