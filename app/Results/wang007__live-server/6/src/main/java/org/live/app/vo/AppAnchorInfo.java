package org.live.app.vo;
 public class AppAnchorInfo {

 private  String anchorUserId;

 private  String headImgUrl;

 private  String account;

 private  String nickname;

 private  String description;

 private  int attentionCount;

 private  boolean attentionFlag;


public void setAnchorUserId(String anchorUserId){
    this.anchorUserId = anchorUserId;
}


public String getAnchorUserId(){
    return anchorUserId;
}


public void setAttentionFlag(boolean attentionFlag){
    this.attentionFlag = attentionFlag;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setHeadImgUrl(String headImgUrl){
    this.headImgUrl = headImgUrl;
}


public String getNickname(){
    return nickname;
}


public int getAttentionCount(){
    return attentionCount;
}


public boolean isAttentionFlag(){
    return attentionFlag;
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


public void setAttentionCount(int attentionCount){
    this.attentionCount = attentionCount;
}


public String getHeadImgUrl(){
    return headImgUrl;
}


}