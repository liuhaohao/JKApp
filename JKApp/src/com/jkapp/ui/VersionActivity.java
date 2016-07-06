package com.jkapp.ui;

import com.jkapp.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VersionActivity extends BaseActivity{

	private TextView tvVersion;
	private String version;
	private ImageView ivUpdate;
	private boolean isUpdate = false;
	
	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_version);
		
		version = getIntent().getStringExtra("version");
		
		findView();
		
		mHandler = new Handler(){
			
			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 0) {
					ivUpdate.clearAnimation();
					isUpdate = false;
					Toast.makeText(VersionActivity.this, "当前已经是最新版本了", Toast.LENGTH_SHORT).show();
				}
			}
		};
	}
	
	private void findView() {
		tvVersion = $(R.id.tvVersion);
		tvVersion.setText("V " + version);
		
		ivUpdate = $(R.id.ivUpdate);
		ivUpdate.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivUpdate:
			checkUpdate();
			break;

		default:
			break;
		}
	}
	
	private void checkUpdate() {
		if(!isUpdate) {
			isUpdate = true;
			Animation anim = AnimationUtils.loadAnimation(this, R.anim.update_anim);
			ivUpdate.startAnimation(anim);
			new UpdateThread().start();
		}
	}
	
	private class UpdateThread extends Thread {
		
		@Override
		public void run() {
			try {
				Thread.sleep(2 * 1000);
				
				mHandler.sendEmptyMessage(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
