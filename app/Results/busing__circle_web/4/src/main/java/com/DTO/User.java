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


public void setImagePath(String imagePath){
    this.imagePath = imagePath;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setImagePath"))

.queryParam("imagePath",imagePath)
;
restTemplate.put(builder.toUriString(),null);
}


}