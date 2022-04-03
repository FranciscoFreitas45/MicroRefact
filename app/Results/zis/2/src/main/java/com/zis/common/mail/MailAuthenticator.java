package com.zis.common.mail;
 import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class MailAuthenticator extends Authenticator{

 private  String username;

 private  String password;

/**
 * 初始化邮箱和密码
 *
 * @param username
 *            邮箱
 * @param password
 *            密码
 */
public MailAuthenticator(String username, String password) {
    this.username = username;
    this.password = password;
}
public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public void setUsername(String username){
    this.username = username;
}


@Override
public PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication(username, password);
}


public String getUsername(){
    return username;
}


}