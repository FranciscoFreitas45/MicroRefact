package com.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getName(){
    return name;
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


public String getMobilePhone(){
    return mobilePhone;
}


public String getLastLoginTime(){
    return lastLoginTime;
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


public String getWeixinOpenid(){
    return weixinOpenid;
}


public String getEmail(){
    return email;
}


public String getImagePath(){
    return imagePath;
}


public String getWeixinImage(){
    return weixinImage;
}


public void setWeixinOpenid(String weixinOpenid){
    this.weixinOpenid = weixinOpenid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWeixinOpenid"))

.queryParam("weixinOpenid",weixinOpenid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNickname(String nickname){
    this.nickname = nickname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNickname"))

.queryParam("nickname",nickname)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWeixinName(String weixinName){
    this.weixinName = weixinName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWeixinName"))

.queryParam("weixinName",weixinName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWeixinImage(String weixinImage){
    this.weixinImage = weixinImage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWeixinImage"))

.queryParam("weixinImage",weixinImage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInviteUserId(Integer inviteUserId){
    this.inviteUserId = inviteUserId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInviteUserId"))

.queryParam("inviteUserId",inviteUserId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInviteCode(String inviteCode){
    this.inviteCode = inviteCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInviteCode"))

.queryParam("inviteCode",inviteCode)
;
restTemplate.put(builder.toUriString(),null);
}


}