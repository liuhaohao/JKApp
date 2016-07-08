package com.jkapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * 意见反馈实体类
 * 
 * @author mao
 *
 */
public class Feedback extends BmobObject {

	private String username;
	
	private String content;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
