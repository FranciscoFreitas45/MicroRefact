package com.gbcom.common.template.xml.mail;
 import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class SmtpAuthentic extends Authenticator{

 private  String username;

 private  String pwd;

/**
 * Creates a new instance of Authentic
 */
public SmtpAuthentic() {
    super();
}/**
 * TODO description here
 *
 * @param name
 *            name
 * @param password
 *            password
 */
public SmtpAuthentic(final String name, final String password) {
    super();
    this.username = name;
    this.pwd = password;
}
public void setUsername(String username){
    this.username = username;
}


public void setPwd(String pwd){
    this.pwd = pwd;
}


@Override
public PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication(getUsername(), getPwd());
}


public String getUsername(){
    return username;
}


public String getPwd(){
    return pwd;
}


}