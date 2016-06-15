package com.jkapp.ui;

import com.jkapp.R;
import com.jkapp.adapter.SlidingMenuAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private DrawerLayout mDrawer;
	private ImageView ivTopTitleLeft;
	private TextView tvTopTitleCenter;
	private ListView main_data_lv;
	private ImageView main_data_add;
	private ListView slide_menu_lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}
	
	private void initView() {
		mDrawer = $(R.id.drawer);
		
		ivTopTitleLeft = $(R.id.ivTopTitleLeft);
		ivTopTitleLeft.setVisibility(View.VISIBLE);
		ivTopTitleLeft.setBackgroundResource(R.drawable.more_menu_icon);
		ivTopTitleLeft.setOnClickListener(this);
		
		tvTopTitleCenter = $(R.id.tvTopTitleCenter);
		tvTopTitleCenter.setText(R.string.app_name);
		
		main_data_lv = $(R.id.main_data_lv);
		
		main_data_add = $(R.id.main_data_add);
		main_data_add.setOnClickListener(this);
		
		
		initSlidingMenu();
	}
	
	private void initSlidingMenu() {
		initPersinality();
		initMenuList();
	}
	
	//打开或者关闭Drawer
	private void toggleDrawer(int gravity) {
		if(mDrawer.isDrawerOpen(gravity)) {
			mDrawer.closeDrawer(gravity);
		} else {
			mDrawer.openDrawer(gravity);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivTopTitleLeft:
			toggleDrawer(Gravity.START);
			break;
		case R.id.main_data_add:
			Intent intent = new Intent(this, PublishActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			if(mDrawer.isDrawerOpen(Gravity.START)) {
				mDrawer.closeDrawer(Gravity.START);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	//初始化侧栏菜单个人信息部分
	private void initPersinality() {
		
	}
	
	//初始化侧栏菜单功能列表部分
	private void initMenuList() {
		slide_menu_lv = $(R.id.slide_menu_lv);
		slide_menu_lv.setAdapter(new SlidingMenuAdapter(getApplicationContext()));
		slide_menu_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//首页
				if(position == 0) {
					toggleDrawer(Gravity.START);
				} else if(position == 1) {
					Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
					startActivity(intent);
				}
			}
		});
	}
}
