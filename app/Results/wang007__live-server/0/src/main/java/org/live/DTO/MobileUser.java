package org.live.DTO;
 import org.live.common.base.BaseEntity;
import org.live.school.entity.Member;
import javax.persistence;
import java.util.Date;
public class MobileUser extends BaseEntity{

 private  String account;

 private  String password;

 private  String nickname;

 private  String email;

 private  String mobileNumber;

 private  Date registerTime;

 private  Member member;

 private  String headImgUrl;

 private  boolean anchorFlag;

 private  boolean lockFlag;

 private  Date lastLoginTime;

 private  String lastLoginIp;

 private  boolean outDateFlag;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getMobileNumber(){
    return mobileNumber;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public Member getMember(){
    return member;
}


public String getAccount(){
    return account;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public Date getRegisterTime(){
    return registerTime;
}


public String getPassword(){
    return password;
}


public String getNickname(){
    return nickname;
}


public String getEmail(){
    return email;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public void setAnchorFlag(boolean anchorFlag){
    this.anchorFlag = anchorFlag;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAnchorFlag"))

.queryParam("anchorFlag",anchorFlag)
;
restTemplate.put(builder.toUriString(),null);
}


}