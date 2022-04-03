package org.live.live.entity;
 import org.live.common.base.BaseEntity;
import org.live.school.entity.Member;
import javax.persistence;
import java.util.Date;
import org.live.Request.MemberRequest;
import org.live.Request.Impl.MemberRequestImpl;
import org.live.DTO.Member;
@Entity
@Table(name = "live_mobileuser")
public class MobileUser extends BaseEntity{

// 账号唯一
@Column(unique = true)
 private  String account;

@Column
 private  String password;

@Column
 private  String nickname;

@Column
 private  String email;

@Column
 private  String mobileNumber;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date registerTime;

@Transient
 private  Member member;

@Column
 private  String headImgUrl;

@Column
 private  boolean anchorFlag;

@Column
 private  boolean lockFlag;

@Column
@Temporal(TemporalType.TIMESTAMP)
 private  Date lastLoginTime;

@Column
 private  String lastLoginIp;

 private  boolean outDateFlag;

@Column(name = "idU3LS")
 private String idU3LS;

@Transient
 private MemberRequest memberrequest = new MemberRequestImpl();;


public void setPassword(String password){
    this.password = password;
}


public String getMobileNumber(){
    return mobileNumber;
}


public void setOutDateFlag(boolean outDateFlag){
    this.outDateFlag = outDateFlag;
}


public void setMobileNumber(String mobileNumber){
    this.mobileNumber = mobileNumber;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public Member getMember(){
  this.member = memberrequest.getMember(this.idU3LS);
return this.member;
}}



public void setLastLoginIp(String lastLoginIp){
    this.lastLoginIp = lastLoginIp;
}


public void setAnchorFlag(boolean anchorFlag){
    this.anchorFlag = anchorFlag;
}


public void setMember(Member member){
this.idU3LS = member.getMember() ;
memberrequest.setMember(member,this.idU3LS);
 this.member = member;
}



public void setHeadImgUrl(String headImgUrl){
    this.headImgUrl = headImgUrl;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
}


public String getAccount(){
    return account;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public boolean isAnchorFlag(){
    return anchorFlag;
}


public void setRegisterTime(Date registerTime){
    this.registerTime = registerTime;
}


public void setLockFlag(boolean lockFlag){
    this.lockFlag = lockFlag;
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


public void setEmail(String email){
    this.email = email;
}


public void setAccount(String account){
    this.account = account;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getEmail(){
    return email;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public boolean isLockFlag(){
    return lockFlag;
}


public boolean isOutDateFlag(){
    return outDateFlag;
}


}