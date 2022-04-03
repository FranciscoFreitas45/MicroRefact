package com.sobey.cmop.mvc.service.basicdata.imports;
 public class ServerBean {

 private  String hostIp;

 private  String displayName;

 private  String innerIp;

 private  String company;

 private  String model;

 private  String rack;

 private  String site;

 private  String location;

 private  String hardware;


public void setHardware(String hardware){
    this.hardware = hardware;
}


public String getModel(){
    return model;
}


public String getLocation(){
    return location;
}


public String getHostIp(){
    return hostIp;
}


public void setInnerIp(String innerIp){
    this.innerIp = innerIp;
}


public void setHostIp(String hostIp){
    this.hostIp = hostIp;
}


public String getInnerIp(){
    return innerIp;
}


public String getRack(){
    return rack;
}


public void setDisplayName(String displayName){
    this.displayName = displayName;
}


public String getHardware(){
    return hardware;
}


public String getCompany(){
    return company;
}


public String getDisplayName(){
    return displayName;
}


public void setLocation(String location){
    this.location = location;
}


public void setRack(String rack){
    this.rack = rack;
}


public void setModel(String model){
    this.model = model;
}


public String getSite(){
    return site;
}


public void setSite(String site){
    this.site = site;
}


@Override
public String toString(){
    return "ServerBean [hostIp=" + hostIp + ", displayName=" + displayName + ", innerIp=" + innerIp + ", company=" + company + ", model=" + model + ", rack=" + rack + ", site=" + site + ", location=" + location + ", hardware=" + hardware + "]";
}


public void setCompany(String company){
    this.company = company;
}


}