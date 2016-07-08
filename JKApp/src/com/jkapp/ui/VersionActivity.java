package com.jkapp.ui;

import java.util.List;

import com.jkapp.R;
import com.jkapp.bean.Version;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class VersionActivity extends BaseActivity{

	private TextView tvVersion;
	private String version;
	private ImageView ivUpdate;
	private boolean isUpdate = false;
	private String url;
	
	private Handler mHandler;
	
	private boolean isShowing = false;
	
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
					update();
				} else if(msg.what == 1) {
					ivUpdate.clearAnimation();
					isUpdate = false;
					Toast.makeText(VersionActivity.this, "当前已经是最新版本了", Toast.LENGTH_SHORT).show();
				} else if(msg.what == 2) {
					ivUpdate.clearAnimation();
					isUpdate = false;
					Toast.makeText(VersionActivity.this, "检查更新失败，请重试", Toast.LENGTH_SHORT).show();
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
				
				//Thread.sleep(2 * 1000);
				BmobQuery<Version> bmobQuery = new BmobQuery<Version>();
				bmobQuery.findObjects(getApplicationContext(), new FindListener<Version>() {
					
					@Override
					public void onSuccess(List<Version> versions) {
						
						if(!isShowing) {
							return;
						}

						String v = version;
						//判断是否可升级
						for(int i = 0; i < versions.size(); i++) {
							if(v.compareTo(versions.get(i).getVersion()) < 0) {
								v = versions.get(i).getVersion();
								url = versions.get(i).getDownloadURL();
							}
						}
						
						System.out.println("v:" + v);
						System.out.println("version:" + version);
						
						if(!v.trim().equals(version.trim())) {
							mHandler.sendEmptyMessage(0);
						} else {
							mHandler.sendEmptyMessage(1);
						}
						
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						System.out.println(arg1);
						if(!isShowing) {
							return;
						}
						
						mHandler.sendEmptyMessage(2);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		builder.setTitle("你确定要更新吗？");
		builder.setNegativeButton("取消", null);
		builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Uri uri = Uri.parse(url);  
				Intent it = new Intent(Intent.ACTION_VIEW, uri);  
				startActivity(it);
			}
		});
		builder.show();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		isShowing = true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		isShowing = false;
	}
}
