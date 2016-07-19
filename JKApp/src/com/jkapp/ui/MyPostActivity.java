package com.jkapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.jkapp.R;
import com.jkapp.adapter.MainDataListViewAdapter;
import com.jkapp.bean.MainData;
import com.jkapp.models.PostInfo;
import com.jkapp.models.config;

public class MyPostActivity extends Activity {

	private ListView mypost;
	private ArrayList<String> postIds = new ArrayList<String>();
	final List<MainData> list = new ArrayList<MainData>();
	final List<MainData> list2 = new ArrayList<MainData>();
	private MainDataListViewAdapter mydapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypost);
		
		init();
	}

	private void init() {
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("我的发布");
		Bmob.initialize(getApplicationContext(), config.applicationId);

		mypost = (ListView) findViewById(R.id.myPost);

		Intent it = super.getIntent();
		postIds = it.getStringArrayListExtra("ids");
		showPost();
		setListener();
	}

	private void setListener() {
		mypost.setOnItemClickListener(new OnDataListViewItemClickListener());
	}

	private void showPost() {
		BmobQuery<PostInfo> query = new BmobQuery<PostInfo>();
		query.addWhereContainedIn("objectId", postIds);
		query.setLimit(50);
		query.findObjects(getApplicationContext(),
				new FindListener<PostInfo>() {

					@Override
					public void onSuccess(List<PostInfo> arg0) {
						if (arg0.size() == 0) {
							Toast.makeText(getApplicationContext(),
									"您还未发布过帖子！", Toast.LENGTH_SHORT).show();
						} else {
							for (PostInfo postInfo : arg0) {
								MainData mainData = new MainData();
								String str1 = postInfo.getObjectId();
								mainData.setObjectId(str1);
								String str2 = postInfo.getTitle();
								mainData.setTitle(str2);
								String str3 = postInfo.getMethod();
								mainData.setMethod(str3);
								String str4 = postInfo.getPublisher();
								mainData.setPublisher(str4);
								Log.d("Test", mainData.getTitle());
								list.add(mainData);
							}
							mydapter = new MainDataListViewAdapter(getApplicationContext(),
									list);
							mypost.setAdapter(mydapter);
						}
					}

					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub

					}
				});
	}

	private class OnDataListViewItemClickListener implements
			OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(MyPostActivity.this,
					CommmentActivity.class);
			// put data
			// ......
			MainData mData = (MainData) mypost.getItemAtPosition(position);

			String title = mData.getTitle();
			String method = mData.getMethod();
			String publisher = mData.getPublisher();
			String postId = mData.getObjectId();

			intent.putExtra("title", title);
			intent.putExtra("method", method);
			intent.putExtra("publisher", publisher);
			intent.putExtra("postId", postId);

			startActivity(intent);
		}

	}

}
