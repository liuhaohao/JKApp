package com.jkapp.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.jkapp.R;
import com.jkapp.adapter.MainDataListViewAdapter;
import com.jkapp.adapter.SlidingMenuAdapter;
import com.jkapp.bean.MainData;
import com.jkapp.models.config;
import com.jkapp.models.userInfo;
import com.jkapp.utils.DataSyncManager;
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
	private Button button;
	private Bitmap bitmap;

	private List<MainData> mainDatas = new ArrayList<MainData>();
	private DataSyncManager dataSyncManager = new DataSyncManager();
	private ArrayList<String> postIds = new ArrayList<String>();
	private MainDataListViewAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();

	}

	private void initView() {
		Bmob.initialize(getApplicationContext(), config.applicationId);
		user = BmobUser.getCurrentUser(getApplicationContext(), userInfo.class);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer);

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

		button = (Button) findViewById(R.id.btnTopTitleRight);
		button.setOnClickListener(this);

		showMainData();
		setListener();
		initSlidingMenu();

	}

	public void showMainData() {
		// TODO Auto-generated method stub
		mainDatas = dataSyncManager.getMainDatas(MainActivity.this);

		mAdapter = new MainDataListViewAdapter(
				MainActivity.this, mainDatas);
		mAdapter.notifyDataSetChanged();
		mAdapter.notifyDataSetInvalidated();

		main_data_lv.setAdapter(mAdapter);
	}

	private void getPostIds() {
		int i = 0;
		for (; i < mainDatas.size(); i++) {
			postIds.add(mainDatas.get(i).getObjectId());
		}
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
			showMainData();
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

			final BmobFile file = new BmobFile(new File(uri.getPath()));
			Toast.makeText(getApplicationContext(), uri.getPath(),
					Toast.LENGTH_SHORT).show();
			file.uploadblock(getApplicationContext(), new UploadFileListener() {

				@Override
				public void onSuccess() {
					final String url = file.getFileUrl(getApplicationContext());
					userInfo user = new userInfo();
					user.setImage(url);
					user.update(getApplicationContext(),
							MainActivity.this.user.getObjectId(),
							new UpdateListener() {

								@Override
								public void onSuccess() {
									Toast.makeText(getApplicationContext(),
											"头像更换成功" + url, Toast.LENGTH_SHORT)
											.show();
								}

								@Override
								public void onFailure(int arg0, String arg1) {

								}
							});
				}

				@Override
				public void onFailure(int arg0, String arg1) {

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
		final Handler handler = new Handler();
		new Thread() {
			@Override
			public void run() {
				bitmap = MainActivity.this.user.getHeadPicture();
				handler.post(runui);
			}
		}.start();

	}

	Runnable runui = new Runnable() {

		@Override
		public void run() {
			if (bitmap != null) {
				slide_menu_headpicture.setImageBitmap(bitmap);
			}
		}
	};

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
					Intent it1 = new Intent(MainActivity.this,MyPostActivity.class);
					getPostIds();
					it1.putStringArrayListExtra("ids", postIds);
					startActivity(it1);
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
					CommmentActivity.class);
			// put data
			// ......
			MainData mData = (MainData) main_data_lv
					.getItemAtPosition(position);

			String title = mData.getTitle();
			String method = mData.getMethod();
			String publisher = mData.getPublisher();
			String postId = mData.getObjectId();

			intent.putExtra("title", title);
			intent.putExtra("method", method);
			intent.putExtra("publisher", publisher);
			intent.putExtra("postId", postId);

			startActivity(intent);
		}

	}
}

// private File getFileFromBitmap(Bitmap bm) throws FileNotFoundException{
// File f = new File(getApplicationContext().getCacheDir(), "img.jpg");
// try {
// f.createNewFile();
// } catch (IOException e) {
// e.printStackTrace();
// }
//
// Bitmap bitmap = bm;
// ByteArrayOutputStream bos = new ByteArrayOutputStream();
// bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
// byte[] bitmapdata = bos.toByteArray();
//
// //write the bytes in file
// FileOutputStream fos = new FileOutputStream(f);
// try {
// fos.write(bitmapdata);
// } catch (IOException e) {
// e.printStackTrace();
// }
// try {
// fos.flush();
// } catch (IOException e) {
// e.printStackTrace();
// }
// try {
// fos.close();
// } catch (IOException e) {
// e.printStackTrace();
// }
// return f;
// }
//
// private Bitmap getSmallBitmap(String path){
// WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//
// int wWid = wm.getDefaultDisplay().getWidth();
// int wHei = wm.getDefaultDisplay().getHeight();
//
// BitmapFactory.Options opts = new BitmapFactory.Options();
// opts.inJustDecodeBounds = true;
// BitmapFactory.decodeFile(path, opts);
// int imgWid = opts.outWidth;
// int imgHei = opts.outHeight;
//
// int scale = 1;
// int scaleX = imgWid/wWid;
// int scaleY = imgHei/wHei;
// if(scaleX > scaleY & scaleY>=1){
// scale = scaleX;
// }
// if(scaleY > scaleX & scaleX>=1){
// scale = scaleY;
// }
// opts.inJustDecodeBounds = false;
// opts.inSampleSize = scale;
// Bitmap bitmap = BitmapFactory.decodeFile(path, opts);
// return bitmap;
// }