package com.sobey.cmop.mvc.entity.ToJson;
 public class ComputeJson {

 private  Integer id;

 private  String identifier;

 private  String computeType;

 private  String osType;

 private  String osBit;

 private  String serverType;

 private  String remark;

 private  String innerIp;

 private  String oldIp;

 private  String hostName;

 private  String serverAlias;

 private  String hostServerAlias;

 private  String osStorageAlias;

 private  String mountESG;


public void setComputeType(String computeType){
    this.computeType = computeType;
}


public void setInnerIp(String innerIp){
    this.innerIp = innerIp;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public String getOsType(){
    return osType;
}


public String getInnerIp(){
    return innerIp;
}


public Integer getId(){
    return id;
}


public void setServerType(String serverType){
    this.serverType = serverType;
}


public void setServerAlias(String serverAlias){
    this.serverAlias = serverAlias;
}


public void setMountESG(String mountESG){
    this.mountESG = mountESG;
}


public String getOldIp(){
    return oldIp;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getIdentifier(){
    return identifier;
}


public void setOldIp(String oldIp){
    this.oldIp = oldIp;
}


public String getRemark(){
    return remark;
}


public void setId(Integer id){
    this.id = id;
}


public String getHostServerAlias(){
    return hostServerAlias;
}


public String getServerAlias(){
    return serverAlias;
}


public String getHostName(){
    return hostName;
}


public void setOsStorageAlias(String osStorageAlias){
    this.osStorageAlias = osStorageAlias;
}


public void setHostServerAlias(String hostServerAlias){
    this.hostServerAlias = hostServerAlias;
}


public String getMountESG(){
    return mountESG;
}


public void setHostName(String hostName){
    this.hostName = hostName;
}


public void setOsType(String osType){
    this.osType = osType;
}


public String getOsStorageAlias(){
    return osStorageAlias;
}


public String getComputeType(){
    return computeType;
}


public String getServerType(){
    return serverType;
}


public void setOsBit(String osBit){
    this.osBit = osBit;
}


public String getOsBit(){
    return osBit;
}


}