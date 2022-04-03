package com.fosun.fc.projects.creepers.DTO;
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


public String getMessageCaptchaKey(){
    if (StringUtils.isBlank(messageCaptchaKey)) {
        return "";
    } else {
        return messageCaptchaKey;
    }
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


}