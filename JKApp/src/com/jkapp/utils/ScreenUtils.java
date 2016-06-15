package com.jkapp.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenUtils {

	private ScreenUtils() {
		throw new UnsupportedOperationException("can't instantiate class ScreenUtils");
	}
	
	public static int getScreenWidthPixels(Activity at) {
		if(at == null) {
			return 0;
		}
		DisplayMetrics metric = new DisplayMetrics();
		at.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.widthPixels;
	}
	
	public static int getScreenHeightPixels(Activity at) {
		if(at == null) {
			return 0;
		}
		DisplayMetrics metric = new DisplayMetrics();
		at.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.heightPixels;
	}
	
	public static int getStatusBarHeight() {
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object obj = clazz.newInstance();
			Field field = clazz.getField("status_bar_height");
			int height = Integer.parseInt(((String)field.get(obj)));
			if(height >= 0) {
				return height;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
