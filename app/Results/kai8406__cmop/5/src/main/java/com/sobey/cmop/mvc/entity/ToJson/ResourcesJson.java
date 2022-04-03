package com.sobey.cmop.mvc.entity.ToJson;
 import java.util.Date;
public class ResourcesJson {

 private  Integer id;

 private  String user;

 private  Integer serviceType;

 private  String serviceTag;

 private  Integer serviceId;

 private  String serviceIdentifier;

 private  Date createTime;

 private  String status;

 private  String ipAddress;

 private  String oldIp;


public Integer getServiceId(){
    return serviceId;
}


public void setServiceId(Integer serviceId){
    this.serviceId = serviceId;
}


public Date getCreateTime(){
    return createTime;
}


public String getIpAddress(){
    return ipAddress;
}


public void setServiceType(Integer serviceType){
    this.serviceType = serviceType;
}


public String getUser(){
    return user;
}


public void setServiceIdentifier(String serviceIdentifier){
    this.serviceIdentifier = serviceIdentifier;
}


public String getServiceTag(){
    return serviceTag;
}


public Integer getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getStatus(){
    return status;
}


public String getServiceIdentifier(){
    return serviceIdentifier;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setStatus(String status){
    this.status = status;
}


public void setServiceTag(String serviceTag){
    this.serviceTag = serviceTag;
}


public String getOldIp(){
    return oldIp;
}


public void setOldIp(String oldIp){
    this.oldIp = oldIp;
}


public void setId(Integer id){
    this.id = id;
}


public void setUser(String user){
    this.user = user;
}


public Integer getServiceType(){
    return serviceType;
}


}