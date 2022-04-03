package com.dtdhehe.ptulife.entity;
 import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class PtuUser implements Serializable{

@Id
 private  String userId;

 private  String userName;

 private  String userPwd;

 private  Integer userStatus;

 private  String orgName;

 private  String orgStatus;

 private  Integer userSex;

 private  String trueName;

 private  String mobileNum;

 private  String qqNum;

 private  String wechatNum;

 private  String nickName;

 private  String motto;

 private  String headImg;

 private  String email;

 private  String valid;


public String getMotto(){
    return motto;
}


public String getNickName(){
    return nickName;
}


public void setTrueName(String trueName){
    this.trueName = trueName;
}


public void setUserPwd(String userPwd){
    this.userPwd = userPwd;
}


public void setWechatNum(String wechatNum){
    this.wechatNum = wechatNum;
}


public String getValid(){
    return valid;
}


public Integer getUserStatus(){
    return userStatus;
}


public void setMotto(String motto){
    this.motto = motto;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getUserName(){
    return userName;
}


public String getQqNum(){
    return qqNum;
}


public void setUserStatus(Integer userStatus){
    this.userStatus = userStatus;
}


public void setQqNum(String qqNum){
    this.qqNum = qqNum;
}


public String getOrgName(){
    return orgName;
}


public void setUserSex(Integer userSex){
    this.userSex = userSex;
}


public void setValid(String valid){
    this.valid = valid;
}


public Integer getUserSex(){
    return userSex;
}


public void setHeadImg(String headImg){
    this.headImg = headImg;
}


public void setOrgName(String orgName){
    this.orgName = orgName;
}


public void setNickName(String nickName){
    this.nickName = nickName;
}


public String getTrueName(){
    return trueName;
}


public String getWechatNum(){
    return wechatNum;
}


public String getMobileNum(){
    return mobileNum;
}


public void setOrgStatus(String orgStatus){
    this.orgStatus = orgStatus;
}


public void setEmail(String email){
    this.email = email;
}


public String getOrgStatus(){
    return orgStatus;
}


public void setMobileNum(String mobileNum){
    this.mobileNum = mobileNum;
}


public String getEmail(){
    return email;
}


public String getHeadImg(){
    return headImg;
}


@Override
public String toString(){
    return "PtuUser{" + "userId='" + userId + '\'' + ", userName='" + userName + '\'' + ", userPwd='" + userPwd + '\'' + ", userStatus=" + userStatus + ", orgName='" + orgName + '\'' + ", orgStatus='" + orgStatus + '\'' + ", userSex=" + userSex + ", trueName='" + trueName + '\'' + ", mobileNum='" + mobileNum + '\'' + ", qqNum='" + qqNum + '\'' + ", wechatNum='" + wechatNum + '\'' + ", nickName='" + nickName + '\'' + ", motto='" + motto + '\'' + '}';
}


public String getUserPwd(){
    return userPwd;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}