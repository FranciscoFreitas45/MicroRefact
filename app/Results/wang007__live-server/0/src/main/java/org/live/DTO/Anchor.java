package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
public class Anchor extends BaseEntity{

 private  MobileUser user;

 private  String idCard;

 private  String realName;

 private  Date createTime;

 private  String description;

 private String idQ2RW;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public Date getCreateTime(){
    return createTime;
}


public String getIdCard(){
    return idCard;
}


public MobileUser getUser(){
    return user;
}


public String getRealName(){
    return realName;
}


public String getDescription(){
    return description;
}


public void setUser(MobileUser user){
this.idQ2RW = user.getUser() ;
mobileuserrequest.setUser(user,this.idQ2RW);
 this.user = user;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreateTime"))

.queryParam("createTime",createTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRealName(String realName){
    this.realName = realName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRealName"))

.queryParam("realName",realName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIdCard(String idCard){
    this.idCard = idCard;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIdCard"))

.queryParam("idCard",idCard)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


}