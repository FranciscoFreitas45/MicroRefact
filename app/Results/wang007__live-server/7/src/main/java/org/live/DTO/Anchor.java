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
  this.user = mobileuserrequest.getUser(this.idQ2RW);
return this.user;
}}



public String getRealName(){
    return realName;
}


public String getDescription(){
    return description;
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


}