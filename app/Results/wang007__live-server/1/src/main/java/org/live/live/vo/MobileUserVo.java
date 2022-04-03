package org.live.live.vo;
 import java.util.Date;
public class MobileUserVo {

 private  String id;

 private  String headImgUrl;

 private  String account;

 private  String nickname;

 private  String email;

 private  String mobileNumber;

 private  Date registerTime;

 private  boolean anchorFlag;

 private  boolean lockFlag;

 private  Date lastLoginTime;

 private  String lastLoginIp;

 private  String memberNo;

 private  String realName;

 private  String sex;

 private  String memberType;

 private  String className;

 private  Integer age;

 private  Date birthday;

 private  String memberId;

public MobileUserVo(String id, String headImgUrl, String account, String nickname, String email, String mobileNumber, Date registerTime, boolean anchorFlag, boolean lockFlag, Date lastLoginTime, String lastLoginIp, String memberNo, String realName, String sex, String memberType, String className, Integer age, Date birthday, String memberId) {
    this.id = id;
    this.headImgUrl = headImgUrl;
    this.account = account;
    this.nickname = nickname;
    this.email = email;
    this.mobileNumber = mobileNumber;
    this.registerTime = registerTime;
    this.anchorFlag = anchorFlag;
    this.lockFlag = lockFlag;
    this.lastLoginTime = lastLoginTime;
    this.lastLoginIp = lastLoginIp;
    this.memberNo = memberNo;
    this.realName = realName;
    this.sex = sex;
    this.memberType = memberType;
    this.className = className;
    this.age = age;
    this.birthday = birthday;
    this.memberId = memberId;
}
public String getMobileNumber(){
    return mobileNumber;
}


public void setRealName(String realName){
    this.realName = realName;
}


public Integer getAge(){
    return age;
}


public void setMemberType(String memberType){
    this.memberType = memberType;
}


public void setMobileNumber(String mobileNumber){
    this.mobileNumber = mobileNumber;
}


public String getLastLoginIp(){
    return lastLoginIp;
}


public void setLastLoginIp(String lastLoginIp){
    this.lastLoginIp = lastLoginIp;
}


public String getId(){
    return id;
}


public void setClassName(String className){
    this.className = className;
}


public void setMemberId(String memberId){
    this.memberId = memberId;
}


public void setAnchorFlag(boolean anchorFlag){
    this.anchorFlag = anchorFlag;
}


public void setHeadImgUrl(String headImgUrl){
    this.headImgUrl = headImgUrl;
}


public String getMemberNo(){
    return memberNo;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
}


public String getAccount(){
    return account;
}


public void setId(String id){
    this.id = id;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public boolean isAnchorFlag(){
    return anchorFlag;
}


public void setMemberNo(String memberNo){
    this.memberNo = memberNo;
}


public void setRegisterTime(Date registerTime){
    this.registerTime = registerTime;
}


public void setLockFlag(boolean lockFlag){
    this.lockFlag = lockFlag;
}


public Date getBirthday(){
    return birthday;
}


public void setSex(String sex){
    this.sex = sex;
}


public String getRealName(){
    return realName;
}


public Date getRegisterTime(){
    return registerTime;
}


public void setBirthday(Date birthday){
    this.birthday = birthday;
}


public String getMemberId(){
    return memberId;
}


public String getNickname(){
    return nickname;
}


public void setEmail(String email){
    this.email = email;
}


public String getSex(){
    return sex;
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


public String getMemberType(){
    return memberType;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public String getClassName(){
    return className;
}


public boolean isLockFlag(){
    return lockFlag;
}


public void setAge(Integer age){
    this.age = age;
}


}