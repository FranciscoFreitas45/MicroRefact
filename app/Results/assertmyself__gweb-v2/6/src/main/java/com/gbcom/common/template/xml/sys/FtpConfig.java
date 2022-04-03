package com.gbcom.common.template.xml.sys;
 import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
public class FtpConfig {

@XStreamAsAttribute
 private  String ftpDir;

@XStreamAsAttribute
 private  String ftpIp;

@XStreamAsAttribute
 private  String ftpPort;

@XStreamAsAttribute
 private  String ftpWIp;

@XStreamAsAttribute
 private  String ftpWport;

@XStreamAsAttribute
 private  String userName;

@XStreamAsAttribute
 private  String pwd;


public String getFtpIp(){
    return ftpIp;
}


public void setFtpWport(String ftpWport){
    this.ftpWport = ftpWport;
}


public String getFtpPort(){
    return ftpPort;
}


public String getFtpWIp(){
    return ftpWIp;
}


public String getFtpWport(){
    return ftpWport;
}


public String getPwd(){
    return pwd;
}


public void setFtpWIp(String ftpWIp){
    this.ftpWIp = ftpWIp;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setFtpIp(String ftpIp){
    this.ftpIp = ftpIp;
}


public String getUserName(){
    return userName;
}


public void setPwd(String pwd){
    this.pwd = pwd;
}


@Override
public String toString(){
    return "ftpIp=" + this.ftpIp + "  ftpDir=" + this.ftpDir + "   userName=" + this.userName + "   pwd=" + this.pwd;
}


public void setFtpDir(String ftpDir){
    this.ftpDir = ftpDir;
}


public String getFtpDir(){
    return ftpDir;
}


public void setFtpPort(String ftpPort){
    this.ftpPort = ftpPort;
}


}