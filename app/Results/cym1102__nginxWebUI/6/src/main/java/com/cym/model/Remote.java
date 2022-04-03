package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
@Table
public class Remote extends BaseModel{

 private String protocol;

 private String ip;

 private Integer port;

@InitValue("0")
 private Integer status;

 private String creditKey;

 private String name;

 private String pass;

 private String version;

 private String system;

 private String descr;

@InitValue("0")
 private Integer monitor;

 private String parentId;

 private Integer type;

 private Integer nginx;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getPass(){
    return pass;
}


public String getDescr(){
    return descr;
}


public String getIp(){
    return ip;
}


public void setProtocol(String protocol){
    this.protocol = protocol;
}


public Integer getStatus(){
    return status;
}


public void setPort(Integer port){
    this.port = port;
}


public String getCreditKey(){
    return creditKey;
}


public void setCreditKey(String creditKey){
    this.creditKey = creditKey;
}


public String getSystem(){
    return system;
}


public Integer getMonitor(){
    return monitor;
}


public void setSystem(String system){
    this.system = system;
}


public void setPass(String pass){
    this.pass = pass;
}


public String getVersion(){
    return version;
}


public void setDescr(String descr){
    this.descr = descr;
}


public void setVersion(String version){
    this.version = version;
}


public String getProtocol(){
    return protocol;
}


public void setType(Integer type){
    this.type = type;
}


public void setIp(String ip){
    this.ip = ip;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setNginx(Integer nginx){
    this.nginx = nginx;
}


public Integer getPort(){
    return port;
}


public Integer getType(){
    return type;
}


public void setMonitor(Integer monitor){
    this.monitor = monitor;
}


public Integer getNginx(){
    return nginx;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public String getParentId(){
    return parentId;
}


}