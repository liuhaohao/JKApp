package com.jkapp.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

import com.jkapp.R;
import com.jkapp.adapter.SlidingMenuAdapter;
import com.jkapp.models.config;
import com.jkapp.models.userInfo;
import com.jkapp.widget.CircleImageView;

public class MainActivity extends Activity implements OnClickListener {

	private DrawerLayout mDrawer;
	private ImageView ivTopTitleLeft;
	private TextView tvTopTitleCenter;
	private ListView main_data_lv;
	private ImageView main_data_add;
	private ListView slide_menu_lv;
	private CircleImageView slide_menu_headpicture;
	private TextView slide_menu_username;
	private userInfo user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		Bmob.initialize(getApplicationContext(), config.applicationId);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer);
		user = BmobUser.getCurrentUser(getApplicationContext(), userInfo.class);

		ivTopTitleLeft = (ImageView) findViewById(R.id.ivTopTitleLeft);
		ivTopTitleLeft.setVisibility(View.VISIBLE);
		ivTopTitleLeft.setBackgroundResource(R.drawable.more_menu_icon);

		tvTopTitleCenter = (TextView) findViewById(R.id.tvTopTitleCenter);
		tvTopTitleCenter.setText(R.string.app_name);

		main_data_lv = (ListView) findViewById(R.id.main_data_lv);
		main_data_lv
				.setOnItemClickListener(new OnDataListViewItemClickListener());

		main_data_add = (ImageView) findViewById(R.id.main_data_add);

		slide_menu_lv = (ListView) findViewById(R.id.slide_menu_lv);
		slide_menu_headpicture = (CircleImageView) findViewById(R.id.slide_menu_headpicture);
		slide_menu_username = (TextView) this
				.findViewById(R.id.slide_menu_username);
		setListener();
		initSlidingMenu();

	}

	private void setListener() {
		ivTopTitleLeft.setOnClickListener(this);
		main_data_add.setOnClickListener(this);
		slide_menu_username.setOnClickListener(this);
		slide_menu_headpicture.setOnClickListener(this);
	}

	private void initSlidingMenu() {
		initPersinality();
		initMenuList();
	}

	// 打开或者关闭Drawer
	private void toggleDrawer(int gravity) {
		if (mDrawer.isDrawerOpen(gravity)) {
			mDrawer.closeDrawer(gravity);
		} else {
			mDrawer.openDrawer(gravity);
		}
	}

	private Bitmap getBitmapFromUri(String uri)  {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
		opts.inSampleSize = 10;
		opts.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeFile(uri, opts);
		
//		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//		int wmWidth = wm.getDefaultDisplay().getWidth();
//		int wmHeight = wm.getDefaultDisplay().getHeight();
		
//		int scaleX = bitmap.getWidth()/wmWidth;
//		int scaleY = bitmap.getHeight()/wmHeight;
//		if(scaleX>scaleY & scaleY>=1) opts.inSampleSize = scaleX;
//		if(scaleY>scaleX & scaleX>=1) opts.inSampleSize = scaleY;
		return bitmap;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivTopTitleLeft:
			toggleDrawer(Gravity.START);
			break;
		case R.id.main_data_add:
			Intent i1 = new Intent(this, PublishActivity.class);
			startActivity(i1);
			break;
		case R.id.slide_menu_headpicture:
			changeHeadPicture();
			break;
		default:
			break;
		}
	}

	private void changeHeadPicture() {
		Intent it = new Intent();
		it.setAction(Intent.ACTION_PICK);
		it.setType("image/*");
		this.startActivityForResult(it, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (data != null && requestCode == 0 && resultCode == -1) {

			Uri uri = data.getData();
			this.slide_menu_headpicture.setImageURI(uri);

			userInfo newuser = new userInfo();
			newuser.setHeadImage(uri.toString());
			newuser.update(getApplicationContext(), user.getObjectId(),
					new UpdateListener() {
						@Override
						public void onSuccess() {
							Toast.makeText(getApplicationContext(), "头像更换成功",
									Toast.LENGTH_SHORT).show();
						}

						@Override
						public void onFailure(int arg0, String arg1) {
							Toast.makeText(getApplicationContext(),
									"头像更换失败" + arg1, Toast.LENGTH_SHORT).show();
						}
					});
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mDrawer.isDrawerOpen(Gravity.START)) {
				mDrawer.closeDrawer(Gravity.START);
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	// 初始化侧栏菜单个人信息部分
	private void initPersinality() {
		this.slide_menu_username.setText(user.getNickName());
//		String uri = this.user.getHeadImage();
//		this.slide_menu_headpicture.setImageBitmap(getBitmapFromUri(uri));
	}

	// 初始化侧栏菜单功能列表部分
	private void initMenuList() {
		slide_menu_lv
				.setAdapter(new SlidingMenuAdapter(getApplicationContext()));
		slide_menu_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					Intent it = new Intent(getApplicationContext(),
							SetPersonInfoActivity.class);
					startActivity(it);
					break;
				case 1:
					break;
				case 2:
					toggleDrawer(Gravity.START);
					break;
				case 3:
					Intent intent = new Intent(MainActivity.this,
							SettingsActivity.class);
					startActivity(intent);
					break;
				case 4:
					BmobUser.logOut(getApplicationContext());
					MainActivity.this.finish();
					break;
				}
			}
		});
	}

	private class OnDataListViewItemClickListener implements
			OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(MainActivity.this,
					DataDetailActivity.class);
			// put data
			// ......
			startActivity(intent);
		}

	}
}
