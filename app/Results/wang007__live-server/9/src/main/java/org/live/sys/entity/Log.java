package org.live.sys.entity;
 import org.live.common.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "sys_log")
public class Log extends BaseEntity{

@Column
 private  String ip;

@Column
 private  String name;

@Column
 private  String username;

@Column
 private  Date handleTime;

@Column
 private  String description;

@Column
 private  String logLevel;

@Column
 private  String operateType;


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


public String getLogLevel(){
    return logLevel;
}


public Date getHandleTime(){
    return handleTime;
}


}