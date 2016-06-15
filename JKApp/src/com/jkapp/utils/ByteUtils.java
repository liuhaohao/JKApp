package com.jkapp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class ByteUtils {

	private ByteUtils() {
		throw new UnsupportedOperationException("can't instantiate class ByteUtils");
	}
	
	public static String bytes2HexString(byte[] data) {
		if(data == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(2 * data.length);
		for(byte b : data) {
			int n = 0x00ff & b;
			if(n < 10) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(n));
		}
		return sb.toString();
	}
	
	public static String bytes2String(byte[] data) {
		return bytes2String(data, null);
	}

	public static String bytes2String(byte[] data, Charset charset) {
		if(data == null) {
			return null;
		}
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(data);
			return IoUtils.inputStream2String(bais, charset);
		} finally {
			IoUtils.close(bais);
		}
	}

	public static byte[] string2Bytes(String s) {
		return string2Bytes(s, null);
	}
	
	public static byte[] string2Bytes(String s, String charsetName) {
		if(s == null) {
			return null;
		}
		if(charsetName == null) {
			return s.getBytes();
		} else {
			try {
				return s.getBytes(charsetName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	

	public static byte[] bitmap2Bytes(Bitmap bitmap) {
		if(bitmap == null) {
			return null;
		}
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream(BitmapUtils.sizeOfBitmap(bitmap));
			bitmap.compress(CompressFormat.JPEG, 100, baos);
			return baos.toByteArray();
		} finally {
			IoUtils.close(baos);
		}
	}
	
	public static Bitmap bytes2Bitmap(byte[] data) {
		if(data == null) {
			return null;
		}
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

}
