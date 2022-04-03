package org.gliderwiki.web.vo;
 public class MemberSessionVo extends BaseObjectBean{

 private  long serialVersionUID;

 public  GuestUser GUEST_USER;

 private  Integer weUserIdx;

 private  String weUserId;

 private  String weUserName;

 private  String weUserKey;

 private  String weUserPwd;

 private  String weUserNick;

 private  String weUserAuthYn;

 private  String weUserEmail;

 private  String weUserSite;

 private  String weThumbPath;

 private  String weThumbName;

 private  Integer weGrade;

 private  String weTechYn;

 private  Integer wePoint;

 private  long serialVersionUID;

 private  GuestUser guestUser;

public MemberSessionVo() {
}public MemberSessionVo(String weUserId, String weUserName) {
    this.weUserId = weUserId;
    this.weUserName = weUserName;
}public MemberSessionVo(Integer weUserIdx, String weUserId, String weUserName) {
    this.weUserIdx = weUserIdx;
    this.weUserId = weUserId;
    this.weUserName = weUserName;
}
public String getWeUserId(){
    return weUserId;
}


public String getWeUserName(){
    return weUserName;
}


public void setWeUserNick(String weUserNick){
    this.weUserNick = weUserNick;
}


@Override
public boolean isGuest(){
    return true;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public String getWeUserPwd(){
    return weUserPwd;
}


public String getWeUserSite(){
    return weUserSite;
}


public String getWeThumbName(){
    return weThumbName;
}


public void setWeTechYn(String weTechYn){
    this.weTechYn = weTechYn;
}


public void setWeUserId(String weUserId){
    this.weUserId = weUserId;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public void setWeUserPwd(String weUserPwd){
    this.weUserPwd = weUserPwd;
}


public void setWePoint(Integer wePoint){
    this.wePoint = wePoint;
}


public void setWeUserName(String weUserName){
    this.weUserName = weUserName;
}


public void setWeThumbPath(String weThumbPath){
    this.weThumbPath = weThumbPath;
}


public void setWeGrade(Integer weGrade){
    this.weGrade = weGrade;
}


public String getWeTechYn(){
    return weTechYn;
}


public void setWeUserEmail(String weUserEmail){
    this.weUserEmail = weUserEmail;
}


public void setWeUserAuthYn(String weUserAuthYn){
    this.weUserAuthYn = weUserAuthYn;
}


public void setWeUserSite(String weUserSite){
    this.weUserSite = weUserSite;
}


public String getWeThumbPath(){
    return weThumbPath;
}


public void setWeThumbName(String weThumbName){
    this.weThumbName = weThumbName;
}


public String getWeUserNick(){
    return weUserNick;
}


public Integer getWePoint(){
    return wePoint;
}


public GuestUser getGuestUser(){
    return guestUser;
}


public String getWeUserEmail(){
    return weUserEmail;
}


public String getWeUserKey(){
    return weUserKey;
}


public Integer getWeGrade(){
    return weGrade;
}


public String getWeUserAuthYn(){
    return weUserAuthYn;
}


public void setWeUserKey(String weUserKey){
    this.weUserKey = weUserKey;
}


}