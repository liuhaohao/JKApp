package com.jkapp.models;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {

	private String postId;
	private String detail;
	private String name;
	
	public Comment(String postId,String detail,String name){
		this.detail = detail;
		this.postId = postId;
		this.name = name;
	}
	
	public Comment(){
	}
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
