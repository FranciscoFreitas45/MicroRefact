package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_system_message")
@org.hibernate.annotations.Proxy(lazy = false)
public class SystemMessage {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String msgtype;

 private  String smtpserver;

 private  String smtpuser;

 private  String smtppassword;

 private  String mailfrom;

 private  String seclev;

 private  String sslport;

 private  String orgi;

 private  String smstype;

 private  String url;

 private  String appkey;

 private  String appsec;

 private  String sign;

 private  Date createtime;

 private  String tpcode;

 private  String moreparam;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setSslport(String sslport){
    this.sslport = sslport;
}


public String getSmtppassword(){
    return smtppassword;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getMoreparam(){
    return moreparam;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getSslport(){
    return sslport;
}


public String getSmtpuser(){
    return smtpuser;
}


public void setSmtpserver(String smtpserver){
    this.smtpserver = smtpserver;
}


public void setAppkey(String appkey){
    this.appkey = appkey;
}


public String getMailfrom(){
    return mailfrom;
}


public Date getCreatetime(){
    return createtime;
}


public String getSmstype(){
    return smstype;
}


public void setId(String id){
    this.id = id;
}


public void setSmtppassword(String smtppassword){
    this.smtppassword = smtppassword;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setMsgtype(String msgtype){
    this.msgtype = msgtype;
}


public void setMailfrom(String mailfrom){
    this.mailfrom = mailfrom;
}


public void setAppsec(String appsec){
    this.appsec = appsec;
}


public void setSmstype(String smstype){
    this.smstype = smstype;
}


public String getAppkey(){
    return appkey;
}


public void setMoreparam(String moreparam){
    this.moreparam = moreparam;
}


public String getSmtpserver(){
    return smtpserver;
}


public void setSeclev(String seclev){
    this.seclev = seclev;
}


public void setUrl(String url){
    this.url = url;
}


public void setSign(String sign){
    this.sign = sign;
}


public String getSeclev(){
    return seclev;
}


public String getUrl(){
    return url;
}


public String getSign(){
    return sign;
}


public String getMsgtype(){
    return msgtype;
}


public void setSmtpuser(String smtpuser){
    this.smtpuser = smtpuser;
}


public String getOrgi(){
    return orgi;
}


public String getAppsec(){
    return appsec;
}


public void setTpcode(String tpcode){
    this.tpcode = tpcode;
}


public String getTpcode(){
    return tpcode;
}


}