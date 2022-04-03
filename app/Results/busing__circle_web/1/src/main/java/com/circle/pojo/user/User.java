package com.circle.pojo.user;
 public class User {

 private  int id;

 private  String mobilePhone;

 private  String name;

 private  String nickname;

 private  String password;

 private  String email;

 private  String headImage;

 private  int status;

 private  String registerTime;

 private  String lastLoginTime;

 private  String imagePath;

 private  String inviteCode;

 private  Integer inviteUserId;

 private  String weixinImage;

 private  String weixinName;

 private  String weixinOpenid;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public void setInviteUserId(Integer inviteUserId){
    this.inviteUserId = inviteUserId;
}


public void setWeixinOpenid(String weixinOpenid){
    this.weixinOpenid = weixinOpenid;
}


public Integer getInviteUserId(){
    return inviteUserId;
}


public String getWeixinName(){
    return weixinName;
}


public int getId(){
    return id;
}


public int getStatus(){
    return status;
}


public String getHeadImage(){
    return headImage;
}


public void setLastLoginTime(String lastLoginTime){
    this.lastLoginTime = lastLoginTime;
}


public void setId(int id){
    this.id = id;
}


public String getMobilePhone(){
    return mobilePhone;
}


public String getLastLoginTime(){
    return lastLoginTime;
}


public void setInviteCode(String inviteCode){
    this.inviteCode = inviteCode;
}


public void setWeixinName(String weixinName){
    this.weixinName = weixinName;
}


public void setRegisterTime(String registerTime){
    this.registerTime = registerTime;
}


public void setHeadImage(String headImage){
    this.headImage = headImage;
}


public void setWeixinImage(String weixinImage){
    this.weixinImage = weixinImage;
}


public void setStatus(int status){
    this.status = status;
}


public String getRegisterTime(){
    return registerTime;
}


public String getInviteCode(){
    return inviteCode;
}


public String getPassword(){
    return password;
}


public String getNickname(){
    return nickname;
}


public void setEmail(String email){
    this.email = email;
}


public String getWeixinOpenid(){
    return weixinOpenid;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getEmail(){
    return email;
}


public String getImagePath(){
    return imagePath;
}


public void setImagePath(String imagePath){
    this.imagePath = imagePath;
}


public String getWeixinImage(){
    return weixinImage;
}


public void setMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
}


}