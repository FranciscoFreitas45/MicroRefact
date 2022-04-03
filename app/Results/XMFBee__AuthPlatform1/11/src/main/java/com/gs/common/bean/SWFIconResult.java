package com.gs.common.bean;
 import java.io.Serializable;
import java.util.ArrayList;
public class SWFIconResult implements Serializable{

 private  Boolean success;

 private  String userid;

 private  String username;

 private  String msg;

 private  String sourceUrl;

 private  ArrayList avatarUrls;


public void setAvatarUrls(ArrayList avatarUrls){
    this.avatarUrls = avatarUrls;
}


public void setSuccess(Boolean success){
    this.success = success;
}


public String getMsg(){
    return msg;
}


public void setUsername(String username){
    this.username = username;
}


public void setSourceUrl(String sourceUrl){
    this.sourceUrl = sourceUrl;
}


public ArrayList getAvatarUrls(){
    return avatarUrls;
}


public void setMsg(String msg){
    this.msg = msg;
}


public String getUserid(){
    return userid;
}


public String getSourceUrl(){
    return sourceUrl;
}


public Boolean getSuccess(){
    return success;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getUsername(){
    return username;
}


}