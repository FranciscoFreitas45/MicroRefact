package com.dtdhehe.ptulife.DTO;
 import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
public class PtuUser implements Serializable{

 private  String userId;

 private  String userName;

 private  String userPwd;

 private  Integer userStatus;

 private  String orgName;

 private  String orgStatus;

 private  Integer userSex;

 private  String trueName;

 private  String mobileNum;

 private  String qqNum;

 private  String wechatNum;

 private  String nickName;

 private  String motto;

 private  String headImg;

 private  String email;

 private  String valid;


public String getMotto(){
    return motto;
}


public String getNickName(){
    return nickName;
}


public String getValid(){
    return valid;
}


public Integer getUserStatus(){
    return userStatus;
}


public String getUserName(){
    return userName;
}


public String getQqNum(){
    return qqNum;
}


public String getOrgName(){
    return orgName;
}


public Integer getUserSex(){
    return userSex;
}


public String getTrueName(){
    return trueName;
}


public String getWechatNum(){
    return wechatNum;
}


public String getMobileNum(){
    return mobileNum;
}


public String getOrgStatus(){
    return orgStatus;
}


public String getEmail(){
    return email;
}


public String getHeadImg(){
    return headImg;
}


public String getUserPwd(){
    return userPwd;
}


public String getUserId(){
    return userId;
}


}