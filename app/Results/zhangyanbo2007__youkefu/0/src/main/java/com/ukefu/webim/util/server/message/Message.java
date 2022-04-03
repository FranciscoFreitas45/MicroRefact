package com.ukefu.webim.util.server.message;
 import com.ukefu.util.UKTools;
public class Message {

 private  long serialVersionUID;

 private  String id;

 private  String appid;

 private  String userid;

 private  String username;

 private  String touser;

 private  String tousername;

 private  String session;

 private  String sign;

 private  String message;

 private  String orgi;

 private  String agentserviceid;

 private  String channel;


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getAgentserviceid(){
    return agentserviceid;
}


public String getId(){
    return id;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getUsername(){
    return username;
}


public void setTousername(String tousername){
    this.tousername = tousername;
}


public String getChannel(){
    return channel;
}


public void setTouser(String touser){
    this.touser = touser;
}


public void setId(String id){
    this.id = id;
}


public String getTousername(){
    return tousername;
}


public void setSession(String session){
    this.session = session;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getTouser(){
    return touser;
}


public void setUsername(String username){
    this.username = username;
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
}


public String getAppid(){
    return appid;
}


public void setSign(String sign){
    this.sign = sign;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public String getType()


public String getSign(){
    return sign;
}


public String getOrgi(){
    return orgi;
}


public String getSession(){
    return session;
}


}