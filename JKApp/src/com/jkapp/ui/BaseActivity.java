package com.jkapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.bmob.v3.Bmob;

public abstract class BaseActivity extends Activity implements OnClickListener{

	protected static String TAG = BaseActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, "e242efe23c9dabdd705a70b9774c2405");
	}
	
	@Override
	public void onClick(View v) {
		
	}
	
	public <T extends View> T $(int resId) {
		return (T) super.findViewById(resId);
	}
}
