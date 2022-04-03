package com.ipe.module.core.web.security;
 import org.apache.shiro.authc.UsernamePasswordToken;
public class CustUsernamePasswordToken extends UsernamePasswordToken{

 private  String method;

 private  String captcha;

 private  String accessUrl;

public CustUsernamePasswordToken(String username, String password) {
    super(username, password);
}public CustUsernamePasswordToken(String username, String password, String host, String method, String captcha, String accessUrl) {
    super(username, password, host);
    this.method = method;
    this.captcha = captcha;
    this.accessUrl = accessUrl;
}public CustUsernamePasswordToken(String username, char[] password, String host, String method, String captcha, String accessUrl) {
    super(username, password, host);
    this.method = method;
    this.captcha = captcha;
    this.accessUrl = accessUrl;
}
public void setMethod(String method){
    this.method = method;
}


public void setCaptcha(String captcha){
    this.captcha = captcha;
}


public String getCaptcha(){
    return captcha;
}


public String getAccessUrl(){
    return accessUrl;
}


public void setAccessUrl(String accessUrl){
    this.accessUrl = accessUrl;
}


public String getMethod(){
    return method;
}


}