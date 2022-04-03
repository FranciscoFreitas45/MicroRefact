package org.live.DTO;
 import org.live.common.base.BaseEntity;
import javax.persistence;
import java.util.Date;
import org.live.Request.MobileUserRequest;
import org.live.Request.Impl.MobileUserRequestImpl;
import org.live.DTO.MobileUser;
import org.live.Request.LiveCategoryRequest;
import org.live.Request.Impl.LiveCategoryRequestImpl;
import org.live.DTO.LiveCategory;
public class ApplyAnchor extends BaseEntity{

 public  int NOT_AUDIT;

 public  int ADUIT_PASS;

 public  int ADUIT_NOT_PASS;

 public  int ADUIT_IGNORE;

 private  MobileUser user;

 private  LiveCategory category;

 private  String idCard;

 private  String realName;

 private  String idImgUrl;

 private  int passFlag;

 private  String noPassReason;

 private  Date createTime;

 private String id58IK;

 private String idHCR5;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Date getCreateTime(){
    return createTime;
}


public String getIdCard(){
    return idCard;
}


public MobileUser getUser(){
    return user;
}


public LiveCategory getCategory(){
    return category;
}


public String getRealName(){
    return realName;
}


public String getIdImgUrl(){
    return idImgUrl;
}


public String getNoPassReason(){
    return noPassReason;
}


public int getPassFlag(){
    return passFlag;
}


public void setUser(MobileUser user){
this.id58IK = user.getUser() ;
mobileuserrequest.setUser(user,this.id58IK);
 this.user = user;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUser"))

.queryParam("user",user)
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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCreateTime"))

.queryParam("createTime",createTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCategory(LiveCategory category){
this.idHCR5 = category.getCategory() ;
livecategoryrequest.setCategory(category,this.idHCR5);
 this.category = category;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCategory"))

.queryParam("category",category)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIdImgUrl(String idImgUrl){
    this.idImgUrl = idImgUrl;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIdImgUrl"))

.queryParam("idImgUrl",idImgUrl)
;
restTemplate.put(builder.toUriString(),null);
}


}