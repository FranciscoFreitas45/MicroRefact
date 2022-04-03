package org.live.sys.vo;
 import java.util.Date;
public class LogVo {

 private  String id;

 private  String ip;

 private  String name;

 private  String username;

 private  Date handleTime;

 private  String description;

 private  String logLevel;

 private  String operateType;

 private  String beginTime;

 private  String endTime;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public String getOperateType(){
    return operateType;
}


public void setLogLevel(String logLevel){
    this.logLevel = logLevel;
}


public String getIp(){
    return ip;
}


public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public void setHandleTime(Date handleTime){
    this.handleTime = handleTime;
}


public void setIp(String ip){
    this.ip = ip;
}


public String getDescription(){
    return description;
}


public void setOperateType(String operateType){
    this.operateType = operateType;
}


public String getUsername(){
    return username;
}


public String getEndTime(){
    return endTime;
}


public void setEndTime(String endTime){
    this.endTime = endTime;
}


public String getLogLevel(){
    return logLevel;
}


public void setId(String id){
    this.id = id;
}


public void setBeginTime(String beginTime){
    this.beginTime = beginTime;
}


public String getBeginTime(){
    return beginTime;
}


public Date getHandleTime(){
    return handleTime;
}


}