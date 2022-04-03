package org.live.live.vo;
 import javax.persistence.Column;
import java.util.Date;
public class AnchorInfoVo {

 private  String anchorId;

 private  String account;

 private  String nickname;

 private  String realName;

 private  String headImgUrl;

 private  String idCard;

 private  Date createTime;

 private  String description;

 private  String email;

 private  String mobileNumber;

 private  boolean lockFlag;

public AnchorInfoVo() {
}public AnchorInfoVo(String anchorId, String account, String nickname, String realName, String headImgUrl, String idCard, Date createTime, String description, String email, String mobileNumber, boolean lockFlag) {
    this.anchorId = anchorId;
    this.account = account;
    this.nickname = nickname;
    this.realName = realName;
    this.headImgUrl = headImgUrl;
    this.idCard = idCard;
    this.createTime = createTime;
    this.description = description;
    this.email = email;
    this.mobileNumber = mobileNumber;
    this.lockFlag = lockFlag;
}
public void setRealName(String realName){
    this.realName = realName;
}


public String getMobileNumber(){
    return mobileNumber;
}


public Date getCreateTime(){
    return createTime;
}


public void setLockFlag(boolean lockFlag){
    this.lockFlag = lockFlag;
}


public String getIdCard(){
    return idCard;
}


public void setMobileNumber(String mobileNumber){
    this.mobileNumber = mobileNumber;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setDescription(String description){
    this.description = description;
}


public String getRealName(){
    return realName;
}


public String getDescription(){
    return description;
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


public void setEmail(String email){
    this.email = email;
}


public String getAccount(){
    return account;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
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


public void setAnchorId(String anchorId){
    this.anchorId = anchorId;
}


public boolean isLockFlag(){
    return lockFlag;
}


}