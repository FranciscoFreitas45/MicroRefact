package org.gliderwiki.web.vo;
 import java.io.File;
public class ProfileVo extends BaseObjectBean{

 private  Integer weUserIdx;

 private  String weUserNick;

 private  String weUserName;

 private  String weUserKey;

 private  String weUserPwd;

 private  String weUserSite;

 private  String weFileRealName;

 private  String weFileSaveName;

 private  String weFileSavePath;

 private  String weThumbPath;

 private  String weThumbName;

 private  Integer weFileIdx;

 private  String userPassword;

 private  String isUpload;

 private  File fromFilePath;

 private  File toFilePath;


public void setWeUserNick(String weUserNick){
    this.weUserNick = weUserNick;
}


public String getWeUserName(){
    return weUserName;
}


public void setToFilePath(File toFilePath){
    this.toFilePath = toFilePath;
}


public Integer getWeUserIdx(){
    return weUserIdx;
}


public String getWeUserPwd(){
    return weUserPwd;
}


public String getUserPassword(){
    return userPassword;
}


public String getIsUpload(){
    return isUpload;
}


public String getWeUserSite(){
    return weUserSite;
}


public void setWeFileSavePath(String weFileSavePath){
    this.weFileSavePath = weFileSavePath;
}


public String getWeThumbName(){
    return weThumbName;
}


public void setIsUpload(String isUpload){
    this.isUpload = isUpload;
}


public String getWeFileSavePath(){
    return weFileSavePath;
}


public File getFromFilePath(){
    return fromFilePath;
}


public void setWeFileRealName(String weFileRealName){
    this.weFileRealName = weFileRealName;
}


public void setWeUserIdx(Integer weUserIdx){
    this.weUserIdx = weUserIdx;
}


public void setWeUserPwd(String weUserPwd){
    this.weUserPwd = weUserPwd;
}


public void setWeFileIdx(Integer weFileIdx){
    this.weFileIdx = weFileIdx;
}


public String getWeFileRealName(){
    return weFileRealName;
}


public void setFromFilePath(File fromFilePath){
    this.fromFilePath = fromFilePath;
}


public void setWeUserName(String weUserName){
    this.weUserName = weUserName;
}


public void setWeThumbPath(String weThumbPath){
    this.weThumbPath = weThumbPath;
}


public void setWeUserSite(String weUserSite){
    this.weUserSite = weUserSite;
}


public String getWeFileSaveName(){
    return weFileSaveName;
}


public String getWeThumbPath(){
    return weThumbPath;
}


public void setWeThumbName(String weThumbName){
    this.weThumbName = weThumbName;
}


public File getToFilePath(){
    return toFilePath;
}


public Integer getWeFileIdx(){
    return weFileIdx;
}


public String getWeUserNick(){
    return weUserNick;
}


public String getWeUserKey(){
    return weUserKey;
}


public void setWeFileSaveName(String weFileSaveName){
    this.weFileSaveName = weFileSaveName;
}


public void setUserPassword(String userPassword){
    this.userPassword = userPassword;
}


public void setWeUserKey(String weUserKey){
    this.weUserKey = weUserKey;
}


}