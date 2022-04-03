package org.live.DTO;
 import org.live.common.base.BaseEntity;
import org.live.school.entity.Member;
import javax.persistence;
import java.util.Date;
import org.live.Request.MemberRequest;
import org.live.Request.Impl.MemberRequestImpl;
import org.live.DTO.Member;
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

 private String idU3LS;


public String getMobileNumber(){
    return mobileNumber;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public Member getMember(){
  this.member = memberrequest.getMember(this.idU3LS);
return this.member;
}}



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


}