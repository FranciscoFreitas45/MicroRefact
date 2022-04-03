package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsNotice implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String noticeType;

 private  String noticeRole;

 private  String noticeUser;

 private  String noticeInfo;

 private  String readFlag;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getReadFlag(){
    return readFlag;
}


public String getMakeUser(){
    return makeUser;
}


public String getNoticeInfo(){
    return noticeInfo;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public String getNoticeUser(){
    return noticeUser;
}


public String getNoticeType(){
    return noticeType;
}


public Long getId(){
    return id;
}


public String getReserveThird(){
    return reserveThird;
}


public String getReserveFirst(){
    return reserveFirst;
}


public String getReserveSecond(){
    return reserveSecond;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getModifyUser(){
    return modifyUser;
}


public String getNoticeRole(){
    return noticeRole;
}


public String getFlagStatus(){
    return flagStatus;
}


public void setNoticeType(String noticeType){
    this.noticeType = noticeType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeType"))

.queryParam("noticeType",noticeType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNoticeRole(String noticeRole){
    this.noticeRole = noticeRole;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeRole"))

.queryParam("noticeRole",noticeRole)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNoticeUser(String noticeUser){
    this.noticeUser = noticeUser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeUser"))

.queryParam("noticeUser",noticeUser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNoticeInfo(String noticeInfo){
    this.noticeInfo = noticeInfo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNoticeInfo"))

.queryParam("noticeInfo",noticeInfo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setReserveFirst"))

.queryParam("reserveFirst",reserveFirst)
;
restTemplate.put(builder.toUriString(),null);
}


}