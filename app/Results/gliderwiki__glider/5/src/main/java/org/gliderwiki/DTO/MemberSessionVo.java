package org.gliderwiki.DTO;
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


public String getWeTechYn(){
    return weTechYn;
}


public String getWeThumbPath(){
    return weThumbPath;
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


}