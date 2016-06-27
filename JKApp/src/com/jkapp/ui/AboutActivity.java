package com.jkapp.ui;

import com.jkapp.R;

import android.os.Bundle;

public class AboutActivity extends BaseActivity{

	private String version;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
	}
}
