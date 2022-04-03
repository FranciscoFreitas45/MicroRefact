package com.lingxiang2014;
 import org.apache.shiro.authc.UsernamePasswordToken;
public class AuthenticationToken extends UsernamePasswordToken{

 private  long serialVersionUID;

 private  String captchaId;

 private  String captcha;

public AuthenticationToken(String username, String password, String captchaId, String captcha, boolean rememberMe, String host) {
    super(username, password, rememberMe, host);
    this.captchaId = captchaId;
    this.captcha = captcha;
}
public void setCaptcha(String captcha){
    this.captcha = captcha;
}


public String getCaptcha(){
    return captcha;
}


public String getCaptchaId(){
    return captchaId;
}


public void setCaptchaId(String captchaId){
    this.captchaId = captchaId;
}


}