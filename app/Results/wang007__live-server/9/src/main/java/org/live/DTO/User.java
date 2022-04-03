package org.live.DTO;
 import org.live.common.base.BaseEntity;
import org.live.common.constants.Constants;
import org.live.common.constants.UserTypeConstants;
import javax.persistence;
import java.util.Date;
public class User extends BaseEntity{

 private  long serialVersionUID;

 private  String username;

 private  String password;

 private  String userType;

 private  String salt;

 private  String name;

 private  Date registerTime;

 private  int isLock;

 private  int isDelete;

 private  Date lastLoginTime;

 private  String lastLoginIp;

 private  Group group;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


public void setPassword(String password){
    this.password = password;
}


public String getUserType(){
    return userType;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public int getIsDelete(){
    return isDelete;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public String getSalt(){
    return salt;
}


public Date getRegisterTime(){
    return registerTime;
}


public String getUsername(){
    return username;
}


public void setIsDelete(int isDelete){
    this.isDelete = isDelete;
}


public String getPassword(){
    return password;
}


public void setSalt(String salt){
    this.salt = salt;
}


public Group getGroup(){
    return group;
}


public int getIsLock(){
    return isLock;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLastLoginTime"))

.queryParam("lastLoginTime",lastLoginTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLastLoginIp(String lastLoginIp){
    this.lastLoginIp = lastLoginIp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLastLoginIp"))

.queryParam("lastLoginIp",lastLoginIp)
;
restTemplate.put(builder.toUriString(),null);
}


}