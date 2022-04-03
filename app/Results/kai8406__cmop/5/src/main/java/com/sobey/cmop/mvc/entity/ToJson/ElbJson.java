package com.sobey.cmop.mvc.entity.ToJson;
 public class ElbJson {

 private  Integer id;

 private  String identifier;

 private  String keepSession;

 private  String virtualIp;

 private  String oldIp;

 private  String elbPortItems;

 private  String mountComputes;


public String getElbPortItems(){
    return elbPortItems;
}


public void setElbPortItems(String elbPortItems){
    this.elbPortItems = elbPortItems;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public String getKeepSession(){
    return keepSession;
}


public Integer getId(){
    return id;
}


public void setVirtualIp(String virtualIp){
    this.virtualIp = virtualIp;
}


public void setMountComputes(String mountComputes){
    this.mountComputes = mountComputes;
}


public String getOldIp(){
    return oldIp;
}


public String getMountComputes(){
    return mountComputes;
}


public String getIdentifier(){
    return identifier;
}


public void setOldIp(String oldIp){
    this.oldIp = oldIp;
}


public void setKeepSession(String keepSession){
    this.keepSession = keepSession;
}


public String getVirtualIp(){
    return virtualIp;
}


public void setId(Integer id){
    this.id = id;
}


}