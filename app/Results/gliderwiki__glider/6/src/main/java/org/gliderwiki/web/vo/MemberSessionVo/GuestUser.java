package org.gliderwiki.web.vo.MemberSessionVo;
 public class GuestUser extends MemberSessionVo{

 private  long serialVersionUID;

 private  GuestUser guestUser;

GuestUser() {
    guestUser = new GuestUser(0, "GUEST", "GUEST");
}GuestUser(Integer weUserIdx, String weUserId, String weUserName) {
    super(weUserIdx, weUserId, weUserName);
}
public GuestUser getGuestUser(){
    return guestUser;
}


@Override
public boolean isGuest(){
    return true;
}


}