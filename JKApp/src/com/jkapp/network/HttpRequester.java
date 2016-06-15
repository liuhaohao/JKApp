package com.jkapp.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

public class HttpRequester {

	private enum HttpMethod {
		GET, POST
	}
	
	public static String get(String url, HashMap<String, String> parameters) {
		return request(url, parameters, HttpMethod.GET);
	}
	
	public static String post(String url, HashMap<String, String> parameters) {
		return request(url, parameters, HttpMethod.POST);
	}
	
	public static String request(String url, HashMap<String, String> parameters, HttpMethod method) {
		if(TextUtils.isEmpty(url) || method == null) {
			return null;
		}
		StringBuilder parametersString = new StringBuilder();
		if(parameters != null && parameters.size() > 0) {
			Set<Map.Entry<String, String>> entrys = parameters.entrySet();
			for(Map.Entry<String, String> e : entrys) {
				parametersString.append(e.getKey());
				parametersString.append("=");
				parametersString.append(e.getValue());
				parametersString.append("&");
			}
			parametersString.delete(0, parametersString.length() - 1);
		}
		
		HttpURLConnection conn = null;
		try {
			if(method == HttpMethod.GET) {
				url += "?" + parametersString.toString();
			}
			URL netwrokUrl = new URL(url);
			conn = (HttpURLConnection) netwrokUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			if(method == HttpMethod.GET) {
				conn.setRequestMethod("GET");				
			} else if(method == HttpMethod.POST) {
				conn.setRequestMethod("POST");
				OutputStream os = conn.getOutputStream();
				os.write(parametersString.toString().getBytes(Charset.forName("UTF-8")));
				os.flush();
			}
			conn.setConnectTimeout(60 * 1000);
			conn.connect();
			
			InputStream is = conn.getInputStream();
			int length = 0;
			byte[] buffer = new byte[1024];
			StringBuilder sb = new StringBuilder(is.available());
			while((length = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, length));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conn.disconnect();
		}
	}
}
