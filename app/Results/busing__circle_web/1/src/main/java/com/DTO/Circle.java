package com.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getCreateUser(){
    return createUser;
}


public String getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public void setProvince(String province){
    this.province = province;
}


public String getWeixinName(){
    return weixinName;
}


public int getCreateUserId(){
    return createUserId;
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


public void setId(String id){
    this.id = id;
}


public String getMobilePhone(){
    return mobilePhone;
}


public String getAuditMsg(){
    return auditMsg;
}


public void setIssueAddress(String issueAddress){
    this.issueAddress = issueAddress;
}


public String getCity(){
    return city;
}


public void setPostAmPm(int postAmPm){
    this.postAmPm = postAmPm;
}


public String getIssueAddress(){
    return issueAddress;
}


public void setWeixinImage(String weixinImage){
    this.weixinImage = weixinImage;
}


public String getJoinType(){
    return joinType;
}


public void setMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
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


public String getDescription(){
    return description;
}


public String getHeadPath(){
    return headPath;
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


public String getLongitude(){
    return longitude;
}


public String getLatitude(){
    return latitude;
}


public void setCreateUser(String createUser){
    this.createUser = createUser;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateUserId"))

.queryParam("createUserId",createUserId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHeadPath(String headPath){
    this.headPath = headPath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setHeadPath"))

.queryParam("headPath",headPath)
;
restTemplate.put(builder.toUriString(),null);
}


public void setImagePath(String imagePath){
    this.imagePath = imagePath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setImagePath"))

.queryParam("imagePath",imagePath)
;
restTemplate.put(builder.toUriString(),null);
}


}