package org.live.app.vo;
 public class SimpleUserVo {

 private  String userId;

 private  String account;

 private  String nickname;

 private  String headImgUrl;

public SimpleUserVo() {
}public SimpleUserVo(String userId, String account, String nickname, String headImgUrl) {
    this.userId = userId;
    this.account = account;
    this.nickname = nickname;
    this.headImgUrl = headImgUrl;
}
public void setHeadImgUrl(String headImgUrl){
    this.headImgUrl = headImgUrl;
}


public String getNickname(){
    return nickname;
}


public String getAccount(){
    return account;
}


public void setAccount(String account){
    this.account = account;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}