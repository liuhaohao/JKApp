package com.jkapp.utils;

import java.text.DecimalFormat;

import android.content.Context;
import android.util.DisplayMetrics;

public class UnitsUtils {

	private UnitsUtils() {
		throw new UnsupportedOperationException("can't instantiate class UnitsUtils");
	}
	
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int sp2dp(Context context, float spValue) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float scale = metrics.scaledDensity / metrics.density;
		return (int) (spValue / scale + 0.5f);
	}

	public static int dp2sp(Context context, float dpValue) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		float scale = metrics.scaledDensity / metrics.density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	public static String convertByteCount(long size) {
		if(size < 0) {
			return null;
		}
		long KB = 1024;
		long MB = 1024 * KB;
		long GB = 1024 * MB;
		long TB = 1024 * GB;
		DecimalFormat format = new DecimalFormat("#0.00");
		StringBuilder sb = new StringBuilder(8);
		if(size < KB) {
			sb.append(format.format(size));
			sb.append("B");
		} else if(size < MB) {
			sb.append(format.format(size / KB));
			sb.append("KB");
		} else if(size < GB) {
			sb.append(format.format(size / MB));
			sb.append("MB");
		} else if(size < TB) {
			sb.append(format.format(size / GB));
			sb.append("GB");
		} else {
			sb.append(format.format(size / TB));
			sb.append("TB");		
		}
		return sb.toString();
	}
}
