package com.jkapp.ui;

import com.jkapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DataDetailActivity extends BaseActivity{
    private TextView titleTextView;
    private TextView methodTextView;
    String title;
    String method;
    String publisher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datadetail);
		
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		method = intent.getStringExtra("method");
		publisher = intent.getStringExtra("publisher");
		
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("发布");
		setActionBarBack(true);
		
		titleTextView = (TextView) findViewById(R.id.title_textview);
		methodTextView = (TextView) findViewById(R.id.method_textview);
		
		titleTextView.setText(title);
		methodTextView.setText(method);
		
	}
}

