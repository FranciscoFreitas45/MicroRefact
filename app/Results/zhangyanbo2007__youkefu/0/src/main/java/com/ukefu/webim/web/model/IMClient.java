package com.ukefu.webim.web.model;
 public class IMClient {

 private  String user;

 private  String orgi;

 private  String session;

 private  String appid;

 private  String aiid;

 private  String agent;

 private  String skill;

 private  String title;

 private  String url;

 private  String traceid;

 private  String nickname;

 private  String osname;

 private  String browser;


public void setTraceid(String traceid){
    this.traceid = traceid;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public String getAiid(){
    return aiid;
}


public String getUser(){
    return user;
}


public String getTraceid(){
    return traceid;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setAppid(String appid){
    this.appid = appid;
}


public void setOsname(String osname){
    this.osname = osname;
}


public String getTitle(){
    return title;
}


public void setSession(String session){
    this.session = session;
}


public void setBrowser(String browser){
    this.browser = browser;
}


public void setUser(String user){
    this.user = user;
}


public void setTitle(String title){
    this.title = title;
}


public String getAgent(){
    return agent;
}


public String getOsname(){
    return osname;
}


public String getAppid(){
    return appid;
}


public void setUrl(String url){
    this.url = url;
}


public void setAgent(String agent){
    this.agent = agent;
}


public String getUrl(){
    return url;
}


public String getNickname(){
    return nickname;
}


public String getOrgi(){
    return orgi;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getSession(){
    return session;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getBrowser(){
    return browser;
}


public String getSkill(){
    return skill;
}


}