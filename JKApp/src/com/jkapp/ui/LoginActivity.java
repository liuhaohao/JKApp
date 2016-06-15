package com.jkapp.ui;

import com.jkapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initView();
		
		//测试使用，实现登录功能时需要删除该段代码
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	private void initView() {
		((TextView)findViewById(R.id.tvTopTitleCenter)).setText("登录");
	}
}
