package com.jkapp.ui;

import com.jkapp.R;

import android.os.Bundle;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		initView();
		
	}
	
	private void initView() {
		((TextView)findViewById(R.id.tvTopTitleCenter)).setText("注册");
	}
}
