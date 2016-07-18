package com.jkapp.models;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobDate;

public class userInfo extends BmobUser {

	private static final long serialVersionUID = 3336224978454463102L;
	private String nickName;
	private BmobDate birthDay;
	private Integer age;
	private String headImage;
	private String occupation;
	
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
