package com.jkapp.models;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import cn.bmob.v3.BmobUser;

public class userInfo extends BmobUser {

	private static final long serialVersionUID = 3336224978454463102L;
	private String nickName;
	private String headImage;
	private String occupation;
	private String Image;
	
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public Bitmap getHeadPicture(){
		URL url;
		Bitmap bitmap = null;
		if("".equals(Image)){
			return null;
		}
		try {
			url = new URL(Image);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			
		}
		return bitmap;
	}
}
