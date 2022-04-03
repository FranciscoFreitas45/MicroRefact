package com.gs.DTO;
 import java.util.Date;
public class UserRole {

 private  String userRoleId;

 private  String userId;

 private  String roleId;

 private  Date urcreatedTime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


public String getRoleId(){
    return roleId;
}


public String getUserRoleId(){
    return userRoleId;
}


public Date getUrcreatedTime(){
    return urcreatedTime;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserId"))

.queryParam("userId",userId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRoleId(String roleId){
    this.roleId = roleId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRoleId"))

.queryParam("roleId",roleId)
;
restTemplate.put(builder.toUriString(),null);
}


}