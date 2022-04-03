package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreepersLoginParamDTOController {

 private CreepersLoginParamDTO creepersloginparamdto;

 private CreepersLoginParamDTO creepersloginparamdto;


@PutMapping
("/setLoginName")
public void setLoginName(@RequestParam(name = "loginName") String loginName){
creepersloginparamdto.setLoginName(loginName);
}


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "password") String password){
creepersloginparamdto.setPassword(password);
}


@PutMapping
("/setMessageCaptchaValue")
public void setMessageCaptchaValue(@RequestParam(name = "messageCaptchaValue") String messageCaptchaValue){
creepersloginparamdto.setMessageCaptchaValue(messageCaptchaValue);
}


@PutMapping
("/setLoginNameKey")
public void setLoginNameKey(@RequestParam(name = "loginNameKey") String loginNameKey){
creepersloginparamdto.setLoginNameKey(loginNameKey);
}


@PutMapping
("/setPasswordKey")
public void setPasswordKey(@RequestParam(name = "passwordKey") String passwordKey){
creepersloginparamdto.setPasswordKey(passwordKey);
}


@PutMapping
("/setCaptchaKey")
public void setCaptchaKey(@RequestParam(name = "captchaKey") String captchaKey){
creepersloginparamdto.setCaptchaKey(captchaKey);
}


}