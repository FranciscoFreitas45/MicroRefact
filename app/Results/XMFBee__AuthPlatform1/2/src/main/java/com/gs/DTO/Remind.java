package com.gs.DTO;
 import com.gs.Interface.User;
public class Remind {

 private  String remindId;

 private  String remindDes;

 private  String remindUser;

 private  User user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://19";


public String getRemindId(){
    return remindId;
}


public User getUser(){
    return user;
}


public String getRemindUser(){
    return remindUser;
}


public String getRemindDes(){
    return remindDes;
}


public void setRemindDes(String remindDes){
    this.remindDes = remindDes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRemindDes"))

.queryParam("remindDes",remindDes)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRemindUser(String remindUser){
    this.remindUser = remindUser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRemindUser"))

.queryParam("remindUser",remindUser)
;
restTemplate.put(builder.toUriString(),null);
}


}