package org.live.live.vo;
 import java.util.Date;
public class ApplyInfoVo {

 private  String id;

 private  String account;

 private  String nickname;

 private  String idCard;

 private  String realName;

 private  String realNameInMember;

 private  String sex;

 private  String categoryName;

 private  int passFlag;

 private  String noPassReason;

 private  Date createTime;

 private  String idImgUrl;

 private  String email;

 private  String mobileNumber;

public ApplyInfoVo() {
}public ApplyInfoVo(String id, String account, String nickname, String idCard, String realName, String realNameInMember, String sex, String categoryName, int passFlag, String noPassReason, Date createTime, String idImgUrl, String email, String mobileNumber) {
    this.id = id;
    this.account = account;
    this.nickname = nickname;
    this.idCard = idCard;
    this.realName = realName;
    this.realNameInMember = realNameInMember;
    this.sex = sex;
    this.categoryName = categoryName;
    this.passFlag = passFlag;
    this.noPassReason = noPassReason;
    this.createTime = createTime;
    this.idImgUrl = idImgUrl;
    this.email = email;
    this.mobileNumber = mobileNumber;
}
public void setRealName(String realName){
    this.realName = realName;
}


public void setNoPassReason(String noPassReason){
    this.noPassReason = noPassReason;
}


public String getMobileNumber(){
    return mobileNumber;
}


public Date getCreateTime(){
    return createTime;
}


public void setMobileNumber(String mobileNumber){
    this.mobileNumber = mobileNumber;
}


public void setRealNameInMember(String realNameInMember){
    this.realNameInMember = realNameInMember;
}


public String getCategoryName(){
    return categoryName;
}


public String getId(){
    return id;
}


public void setCategoryName(String categoryName){
    this.categoryName = categoryName;
}


public String getAccount(){
    return account;
}


public void setIdCard(String idCard){
    this.idCard = idCard;
}


public void setId(String id){
    this.id = id;
}


public void setPassFlag(int passFlag){
    this.passFlag = passFlag;
}


public String getNoPassReason(){
    return noPassReason;
}


public int getPassFlag(){
    return passFlag;
}


public String getIdCard(){
    return idCard;
}


public void setSex(String sex){
    this.sex = sex;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getRealName(){
    return realName;
}


public String getNickname(){
    return nickname;
}


public void setEmail(String email){
    this.email = email;
}


public void setIdImgUrl(String idImgUrl){
    this.idImgUrl = idImgUrl;
}


public String getRealNameInMember(){
    return realNameInMember;
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


public String getIdImgUrl(){
    return idImgUrl;
}


}