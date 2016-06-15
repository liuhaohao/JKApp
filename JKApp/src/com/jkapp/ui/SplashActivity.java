package com.jkapp.ui;

import com.jkapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends BaseActivity{
	
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		handler = new Handler(){
			
			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 0) {
					Intent i = new Intent(SplashActivity.this, LoginActivity.class);
					startActivity(i);
					finish();
				}
			}
		};
		
		init();
	}
	
	private void init() {
		new Thread() {
			
			@Override
			public void run() {
				
				try {
					
					
					
					//暂停2s
					Thread.sleep(2 * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					handler.sendEmptyMessage(0);
				}
			}
			
		}.start();
	}

}
