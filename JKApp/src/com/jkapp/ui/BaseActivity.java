package com.jkapp.ui;

import com.jkapp.R;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public abstract class BaseActivity extends Activity implements OnClickListener{

	protected static String TAG = BaseActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onClick(View v) {
		
	}
	
	public <T extends View> T $(int resId) {
		return (T) super.findViewById(resId);
	}
	
	/**
	 * Enable or disable the ActionBar back arrow.
	 * if you enable ActionBar back arrow, please
	 * implement {@link #onActionBarBack} method if necessary.
	 * default, The ActionBar back arrow is disable. 
	 * 
	 * @return return true if set successfully,otherwise return false. 
	 */
	public boolean setActionBarBack(boolean enabled) {
		ImageView ivTopTitleLeft = $(R.id.ivTopTitleLeft);
		if(ivTopTitleLeft == null) {
			return false;
		}
		if(enabled) {
			ivTopTitleLeft.setVisibility(View.VISIBLE);
			ivTopTitleLeft.setBackgroundResource(R.drawable.back_icon);
			ivTopTitleLeft.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(!onActionBarBack()) {
						onBackPressed();
					}
				}
			});
		} else {
			ivTopTitleLeft.setVisibility(View.INVISIBLE);
		}
		return true;
	}
	
	/**
	 * if you enable ActionBar back arrow, this method will
	 * be invoked
	 * 
	 * @return if don't want to invoke {{@link #onBackPressed()} you
	 *         must return true. default, return false.
	 */
	protected boolean onActionBarBack() {
		return false;
	}
}
