package com.jkapp.ui;

import com.jkapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PersonalityActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initView();

	}
	
	private void initView() {
		((TextView)findViewById(R.id.tvTopTitleCenter)).setText("个人中心");
		
		setActionBarBack(true);
	}
}
