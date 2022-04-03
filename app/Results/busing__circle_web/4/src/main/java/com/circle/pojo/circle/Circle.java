package com.circle.pojo.circle;
 import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Circle {

 private  String id;

 private  String name;

 private  String description;

 private  String headPath;

 private  String joinType;

 private  String issueTime;

 private  String issueAddress;

 private  String endTime;

 private  String createTime;

 private  String createUser;

 private  String weixinName;

 private  String weixinImage;

 private  int createUserId;

 private  String mobilePhone;

 private  String address;

 private  String province;

 private  String city;

 private  String longitude;

 private  String latitude;

 private  String status;

 private  String auditMsg;

 private  String notice;

 private  Integer isSelfCreate;

 private  String imagePath;

 private  String distance;

 private  int postWeek;

 private  int postAmPm;


public String getCreateUser(){
    return createUser;
}


public String getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public void setJoinType(String joinType){
    this.joinType = joinType;
}


public void setProvince(String province){
    this.province = province;
}


public void setPostWeek(int postWeek){
    this.postWeek = postWeek;
}


public String getWeixinName(){
    return weixinName;
}


public void setNotice(String notice){
    this.notice = notice;
}


public int getCreateUserId(){
    return createUserId;
}


public void main(String[] args){
    try {
        String pwd = "123456";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes());
        String newPwd = new BigInteger(1, md.digest()).toString(16);
        System.out.println(newPwd);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
}


public String getStatus(){
    return status;
}


public int getPostAmPm(){
    return postAmPm;
}


public String getEndTime(){
    return endTime;
}


public void setAuditMsg(String auditMsg){
    this.auditMsg = auditMsg;
}


public void setId(String id){
    this.id = id;
}


public String getMobilePhone(){
    return mobilePhone;
}


public String getAuditMsg(){
    return auditMsg;
}


public void setLongitude(String longitude){
    this.longitude = longitude;
}


public void setIssueAddress(String issueAddress){
    this.issueAddress = issueAddress;
}


public void setIsSelfCreate(Integer isSelfCreate){
    this.isSelfCreate = isSelfCreate;
}


public String getCity(){
    return city;
}


public void setWeixinName(String weixinName){
    this.weixinName = weixinName;
}


public void setPostAmPm(int postAmPm){
    this.postAmPm = postAmPm;
}


public void setCity(String city){
    this.city = city;
}


public String getIssueAddress(){
    return issueAddress;
}


public void setHeadPath(String headPath){
    this.headPath = headPath;
}


public void setWeixinImage(String weixinImage){
    this.weixinImage = weixinImage;
}


public void setDistance(String distance){
    this.distance = distance;
}


public String getJoinType(){
    return joinType;
}


public void setImagePath(String imagePath){
    this.imagePath = imagePath;
}


public void setMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
}


public void setName(String name){
    this.name = name;
}


public void setIssueTime(String issueTime){
    this.issueTime = issueTime;
}


public Integer getIsSelfCreate(){
    return isSelfCreate;
}


public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getHeadPath(){
    return headPath;
}


public void setLatitude(String latitude){
    this.latitude = latitude;
}


public String getNotice(){
    return notice;
}


public int getPostWeek(){
    return postWeek;
}


public String getProvince(){
    return province;
}


public String getAddress(){
    return address;
}


public String getIssueTime(){
    return issueTime;
}


public String getDistance(){
    return distance;
}


public void setAddress(String address){
    this.address = address;
}


public String getLongitude(){
    return longitude;
}


public String getLatitude(){
    return latitude;
}


public void setCreateTime(String createTime){
    this.createTime = createTime;
}


public void setCreateUser(String createUser){
    this.createUser = createUser;
}


public void setStatus(String status){
    this.status = status;
}


public void setEndTime(String endTime){
    this.endTime = endTime;
}


public String getImagePath(){
    return imagePath;
}


public String getWeixinImage(){
    return weixinImage;
}


public void setCreateUserId(int createUserId){
    this.createUserId = createUserId;
}


}