package com.jkapp.adapter;

import java.util.List;

import com.jkapp.R;
import com.jkapp.models.Comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

public class CommentAdapter extends ArrayAdapter<Comment> {

	private int resourceId;

	public CommentAdapter(Context context, int textViewResourceId,
			List<Comment> objects) {
		super(context,  textViewResourceId, objects);
		this.resourceId = textViewResourceId;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Comment comment = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView name = (TextView) view.findViewById(R.id.tv_name);
		TextView time = (TextView) view.findViewById(R.id.tv_time);
		TextView detail = (TextView) view.findViewById(R.id.tv_context);

		name.setText(comment.getName());
		time.setText(comment.getCreatedAt());
		detail.setText(comment.getDetail());

		return view;
	}

}