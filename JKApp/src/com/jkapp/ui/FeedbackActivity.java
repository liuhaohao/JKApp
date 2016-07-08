package com.jkapp.ui;

import com.jkapp.R;
import com.jkapp.bean.Feedback;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;

/**
 * 意见反馈
 * 
 * @author mao
 *
 */
public class FeedbackActivity extends BaseActivity{
	
	private static final String TAG = "FeedbackActivity";
	
	private EditText feedback_et;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_activity);
		
		initView();
	}
	
	private void initView() {
		((TextView)findViewById(R.id.tvTopTitleCenter)).setText("意见反馈");
		setActionBarBack(true);
		TextView tv = ((TextView)findViewById(R.id.tvTopTitleRight));
		tv.setVisibility(View.VISIBLE);
		tv.setText("发送");
		tv.setOnClickListener(this);
		
		feedback_et = (EditText) findViewById(R.id.feedback_et);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvTopTitleRight:
			send();
			break;

		default:
			break;
		}
	}
	
	private void send() {
		String content = feedback_et.getText().toString().trim();
		if(TextUtils.isEmpty(content)) {
			Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_SHORT).show();
		} else {
			Feedback feedback = new Feedback();
			feedback.setContent(content);
			feedback.save(getApplicationContext(), new SaveListener() {
				
				@Override
				public void onSuccess() {
					Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
					finish();
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					Toast.makeText(getApplicationContext(), "发送失败，请重试", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
}
