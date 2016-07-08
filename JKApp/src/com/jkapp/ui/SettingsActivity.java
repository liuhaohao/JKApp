package com.jkapp.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jkapp.AppManager;
import com.jkapp.R;
import com.jkapp.adapter.CommonAdapter;
import com.jkapp.adapter.CommonAdapter.OnCommonAdapterListener;
import com.jkapp.config.Config;
import com.jkapp.utils.FileUtils;
import com.jkapp.utils.UnitsUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends BaseActivity{

	private ListView lv;
	private CommonAdapter<String> mAdapter;
	private List<String> list;
	private List<String> value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		initView();
		
		calculateCacheSize();
	}
	
	private void initView() {
		((TextView)findViewById(R.id.tvTopTitleCenter)).setText("设置");
		setActionBarBack(true);
		
		lv = $(R.id.lv);
		list = new ArrayList<String>();
		list.add("当前版本");
		list.add("清空缓存");
		list.add("意见反馈");
		list.add("关于我们");
		value = new ArrayList<String>();
		value.add(AppManager.getVersion(this));
		value.add("0.0B");
		value.add("");
		value.add("");
		mAdapter = new CommonAdapter<String>(list);
		mAdapter.setmListener(new OnCommonAdapterListener() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder = null;
				if(convertView == null) {
					convertView = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.simple_listview_item, parent, false);
					holder = new ViewHolder();
					holder.tvLeft = (TextView) convertView.findViewById(R.id.tvLeft);
					holder.tvRight = (TextView) convertView.findViewById(R.id.tvRight);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				
				holder.tvLeft.setText(list.get(position));
				holder.tvRight.setText(value.get(position));
				
				return convertView;
			}
			
			class ViewHolder {
				
				TextView tvLeft;
				TextView tvRight;
			}
		});
		lv.setAdapter(mAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position == 0) {
					//当前版本
					Intent intent = new Intent(SettingsActivity.this, VersionActivity.class);
					intent.putExtra("version", value.get(position));
					startActivity(intent);
					
				} else if(position == 1) {
					//清空缓存
					showClearCacheDialog();
				
				} else if(position == 2) {
					//意见反馈
					Intent intent = new Intent(SettingsActivity.this, FeedbackActivity.class);
					startActivity(intent);
					
				} else if(position == 3) {
					//关于我们
					Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
					startActivity(intent);
					
				}
				
			}
		});
	}
	
	private void showClearCacheDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("你真的要清空缓存吗？");
		builder.setNegativeButton("取消", null);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//清空缓存
				try {
					boolean b = FileUtils.deleteFolder(new File(Config.SDCARD_DIR));
					FileUtils.createDirectory(Config.SDCARD_DIR);
					Toast.makeText(getBaseContext(), "清空缓存成功", Toast.LENGTH_SHORT).show();
					value.set(1, "0.00B");
					mAdapter.notifyDataSetChanged();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		builder.show();
	}
	
	//计算缓存大小
	private void calculateCacheSize() {
		new CalculateAsyncTask().execute(new Void[]{});
		
	}
	
	private class CalculateAsyncTask extends AsyncTask<Void, Void, Void> {

		private long size;
		
		@Override
		protected Void doInBackground(Void... params) {
			size = FileUtils.getSize(Config.SDCARD_DIR);
			System.out.println("size:" + size);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			try {
				value.set(1, UnitsUtils.convertByteCount(size));
				mAdapter.notifyDataSetChanged();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
