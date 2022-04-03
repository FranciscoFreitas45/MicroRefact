package com.evolvingreality.onleave.web.websocket.dto;
 public class ActivityDTO {

 private  String sessionId;

 private  String userLogin;

 private  String ipAddress;

 private  String page;

 private  String time;


public void setUserLogin(String userLogin){
    this.userLogin = userLogin;
}


public void setSessionId(String sessionId){
    this.sessionId = sessionId;
}


public String getUserLogin(){
    return userLogin;
}


public String getIpAddress(){
    return ipAddress;
}


public String getPage(){
    return page;
}


public String getTime(){
    return time;
}


@Override
public String toString(){
    return "ActivityDTO{" + "sessionId='" + sessionId + '\'' + ", userLogin='" + userLogin + '\'' + ", ipAddress='" + ipAddress + '\'' + ", page='" + page + '\'' + ", time='" + time + '\'' + '}';
}


public String getSessionId(){
    return sessionId;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setPage(String page){
    this.page = page;
}


public void setTime(String time){
    this.time = time;
}


}