package org.live.live.vo;
 import javax.persistence.Column;
public class AnchorVo {

 private  String anchorId;

 private  String nickname;

 private  String account;

 private  String realName;

 private  String idCard;

 private  String headImgUrl;

 private  boolean lockFlag;

public AnchorVo() {
}public AnchorVo(String anchorId, String nickname, String account, String realName, String idCard, String headImgUrl, boolean lockFlag) {
    this.anchorId = anchorId;
    this.nickname = nickname;
    this.account = account;
    this.realName = realName;
    this.idCard = idCard;
    this.headImgUrl = headImgUrl;
    this.lockFlag = lockFlag;
}
public void setRealName(String realName){
    this.realName = realName;
}


public void setLockFlag(boolean lockFlag){
    this.lockFlag = lockFlag;
}


public String getIdCard(){
    return idCard;
}


public String getRealName(){
    return realName;
}


public String getAnchorId(){
    return anchorId;
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


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setAccount(String account){
    this.account = account;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


public void setAnchorId(String anchorId){
    this.anchorId = anchorId;
}


public boolean isLockFlag(){
    return lockFlag;
}


}