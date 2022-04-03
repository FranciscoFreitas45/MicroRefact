package com.easyshopping;
 import org.apache.shiro.authc.UsernamePasswordToken;
public class AuthenticationToken extends UsernamePasswordToken{

 private  long serialVersionUID;

 private  String captchaId;

 private  String captcha;

/**
 * @param username
 *            用户名
 * @param password
 *            密码
 * @param captchaId
 *            验证码ID
 * @param captcha
 *            验证码
 * @param rememberMe
 *            记住我
 * @param host
 *            IP
 */
public AuthenticationToken(String username, String password, String captchaId, String captcha, boolean rememberMe, String host) {
    super(username, password, rememberMe);
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