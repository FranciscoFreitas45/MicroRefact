package org.live.live.vo;
 import java.util.Date;
public class ApplyVo {

 private  String id;

 private  String account;

 private  String nickname;

 private  String realName;

 private  String categoryName;

 private  int passFlag;

 private  String email;

 private  String mobileNumber;

 private  Date createTime;

public ApplyVo() {
}public ApplyVo(String id, String account, String nickname, String realName, String categoryName, int passFlag, String email, String mobileNumber, Date createTime) {
    this.id = id;
    this.account = account;
    this.nickname = nickname;
    this.realName = realName;
    this.categoryName = categoryName;
    this.passFlag = passFlag;
    this.email = email;
    this.mobileNumber = mobileNumber;
    this.createTime = createTime;
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


public void setMobileNumber(String mobileNumber){
    this.mobileNumber = mobileNumber;
}


public String getCategoryName(){
    return categoryName;
}


public String getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setCategoryName(String categoryName){
    this.categoryName = categoryName;
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


public String getAccount(){
    return account;
}


public void setId(String id){
    this.id = id;
}


public void setAccount(String account){
    this.account = account;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setPassFlag(int passFlag){
    this.passFlag = passFlag;
}


public String getEmail(){
    return email;
}


public int getPassFlag(){
    return passFlag;
}


}