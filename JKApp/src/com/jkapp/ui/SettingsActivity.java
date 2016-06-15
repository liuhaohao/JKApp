package com.jkapp.ui;

import com.jkapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		initView();
	}
	
	private void initView() {
		((TextView)findViewById(R.id.tvTopTitleCenter)).setText("设置");
		setActionBarBack(true);
	}
}
