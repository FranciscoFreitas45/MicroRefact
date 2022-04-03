package com.fosun.fc.projects.creepers.dto;
 import org.apache.commons.lang3.StringUtils;
public class CreepersLoginParamDTO extends CreepersParamDTO{

 private  long serialVersionUID;

 protected  String loginName;

 protected  String loginNameKey;

 protected  String password;

 protected  String passwordKey;

 protected  String captchaKey;

 protected  String captchaValue;

 protected  String captchaId;

 protected  String captchaFilePath;

 protected  String messageCaptchaKey;

 protected  String messageCaptchaValue;


public void setMessageCaptchaKey(String messageCaptchaKey){
    this.messageCaptchaKey = messageCaptchaKey;
}


public void setPassword(String password){
    this.password = password;
}


public void setCaptchaKey(String captchaKey){
    this.captchaKey = captchaKey;
}


public String getMessageCaptchaKey(){
    if (StringUtils.isBlank(messageCaptchaKey)) {
        return "";
    } else {
        return messageCaptchaKey;
    }
}


public void setPasswordKey(String passwordKey){
    this.passwordKey = passwordKey;
}


public String getCaptchaFilePath(){
    return captchaFilePath;
}


public String getLoginNameKey(){
    if (StringUtils.isBlank(loginNameKey)) {
        return "";
    } else {
        return loginNameKey;
    }
}


public void setCaptchaId(String captchaId){
    this.captchaId = captchaId;
}


public String getMessageCaptchaValue(){
    if (StringUtils.isBlank(messageCaptchaValue)) {
        return "";
    } else {
        return messageCaptchaValue;
    }
}


public String getLoginName(){
    if (StringUtils.isBlank(loginName)) {
        return "";
    } else {
        return loginName;
    }
}


public String getPasswordKey(){
    if (StringUtils.isBlank(passwordKey)) {
        return "";
    } else {
        return passwordKey;
    }
}


public String getCaptchaValue(){
    if (StringUtils.isBlank(captchaValue)) {
        return "";
    } else {
        return captchaValue;
    }
}


public String getPassword(){
    if (StringUtils.isBlank(password)) {
        return "";
    } else {
        return password;
    }
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setMessageCaptchaValue(String messageCaptchaValue){
    this.messageCaptchaValue = messageCaptchaValue;
}


public String getCaptchaKey(){
    if (StringUtils.isBlank(captchaKey)) {
        return "";
    } else {
        return captchaKey;
    }
}


public String getCaptchaId(){
    return captchaId;
}


public void setCaptchaFilePath(String captchaFilePath){
    this.captchaFilePath = captchaFilePath;
}


public void setLoginNameKey(String loginNameKey){
    this.loginNameKey = loginNameKey;
}


public void setCaptchaValue(String captchaValue){
    this.captchaValue = captchaValue;
}


}