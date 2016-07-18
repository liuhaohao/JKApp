package com.jkapp.models;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;


public class PostInfo extends BmobObject implements Serializable {

	private static final long serialVersionUID = -5318215751469477041L;

	private String publisher;
	private String title;
	private String method;
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
}
