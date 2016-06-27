package com.jkapp.ui;

import com.jkapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VersionActivity extends BaseActivity{

	private TextView tvVersion;
	private String version;
	private ImageView ivUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_version);
		
		version = getIntent().getStringExtra("version");
		
		findView();
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
		
		
		Toast.makeText(getApplicationContext(), "当前已经是最新版本了", Toast.LENGTH_SHORT).show();
	}
}
