package com.jkapp.network;

import java.io.Serializable;
import java.util.HashMap;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class AsyncHttpRequester {

	private static Handler sHandler;
	
	static {
		sHandler = new Handler(){
			
			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 0) {
					OnHttpRequestListener listener = (OnHttpRequestListener) msg.obj;
					String result = msg.getData().getString("result");
					if(listener != null) {
						listener.onComplete(result);
					}
				}
			}
		};
	}
	
	public interface OnHttpRequestListener extends Serializable{
		
		void onComplete(String result);
	}
	
	public static void get(final String url, final HashMap<String, String> parameters, 
			final OnHttpRequestListener listener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String result = HttpRequester.get(url, parameters);
				Message msg = Message.obtain();
				msg.what = 0;
				msg.obj = listener;
				Bundle data = new Bundle();
				data.putString("result", result);
				msg.setData(data);
				sHandler.sendMessage(msg);
			}
		}).start();
	}
	
	public static void post(final String url, final HashMap<String, String> parameters, 
			final OnHttpRequestListener listener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String result = HttpRequester.post(url, parameters);
				Message msg = Message.obtain();
				msg.what = 0;
				msg.obj = listener;
				Bundle data = new Bundle();
				data.putString("result", result);
				msg.setData(data);
				sHandler.sendMessage(msg);
			}
		}).start();
	}
}
