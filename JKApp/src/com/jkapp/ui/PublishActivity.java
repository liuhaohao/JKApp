package com.jkapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

import com.jkapp.R;
import com.jkapp.models.PostInfo;
import com.jkapp.models.config;
import com.jkapp.models.userInfo;

public class PublishActivity extends Activity implements OnClickListener {

	private EditText title, method;
	private Button publish;
	private userInfo user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);

		initView();
	}

	private void initView() {
		Bmob.initialize(getApplicationContext(), config.applicationId);
		user = BmobUser.getCurrentUser(getApplicationContext(), userInfo.class);

		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("发布");
		// setActionBarBack(true);

		title = (EditText) findViewById(R.id.title);
		method = (EditText) findViewById(R.id.method);
		publish = (Button) findViewById(R.id.publish);
		setListener();
	}

	private void setListener() {
		title.setOnClickListener(this);
		method.setOnClickListener(this);
		publish.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.publish) {
			publish();
		}
	}

	private void publish() {
		String title, method, publisher;
		title = this.title.getText().toString();
		method = this.method.getText().toString();
		publisher = user.getObjectId();

		if ("".equals(title)) {
			Toast.makeText(getApplicationContext(), R.string.problemnullerror,
					Toast.LENGTH_SHORT).show();
			return;
		}
		if ("".equals(method)) {
			Toast.makeText(getApplicationContext(), R.string.methodnullerror,
					Toast.LENGTH_SHORT).show();
			return;
		}
		PostInfo post = new PostInfo();
		post.setTitle(title);
		post.setMethod(method);
		post.setPublisher(publisher);
		post.save(getApplicationContext(), new SaveListener() {
			
			@Override
			public void onSuccess() {
				Toast.makeText(getApplicationContext(),"发布成功",
						Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent(PublishActivity.this,MainActivity.class);
//				startActivity(intent);
				PublishActivity.this.finish();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				Toast.makeText(getApplicationContext(),"发布失败",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
