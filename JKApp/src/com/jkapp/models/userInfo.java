package com.jkapp.models;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;

public class userInfo extends BmobUser {

	private static final long serialVersionUID = 1L;

	private String nickName;
	private BmobDate birthDay;
	private Integer age;
	private String headImage;
	
	
	
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
	public BmobDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(BmobDate birthDay) {
		this.birthDay = birthDay;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
