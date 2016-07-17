package com.jkapp.bean;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject{
      /**
	 * 评论实体类
	 */
	 // private static final long serialVersionUID = 1L;
	  private String userid;
      private String detail;
      private String name;
      //private String answer;
      //private String commenter;
      public String getUserid(){
    	  return userid;
      }
      public String getDetail(){
    	  return detail;
      }
      public String getName(){
    	  return name;
      }
      public void setUserid(String userid){
    	  this.userid=userid;
      }
      public void setDetail(String detail)
      {
    	  this.detail=detail;
      }
      public void setName(String name){
    	  this.name=name;
      }
}
   