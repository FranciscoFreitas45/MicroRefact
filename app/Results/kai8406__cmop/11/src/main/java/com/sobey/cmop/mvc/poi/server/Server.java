package com.sobey.cmop.mvc.poi.server;
 public class Server {

 private  String hostName;

 private  String ipAddress;

 private  String sn;

 private  String gdzcSn;

 private  String type;

 private  String rack;

 private  String model;

 private  String site;


public String getModel(){
    return model;
}


public String getGdzcSn(){
    return gdzcSn;
}


public String getHostName(){
    return hostName;
}


public String getIpAddress(){
    return ipAddress;
}


public String getRack(){
    return rack;
}


public String getSn(){
    return sn;
}


public void setGdzcSn(String gdzcSn){
    this.gdzcSn = gdzcSn;
}


public void setType(String type){
    this.type = type;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setHostName(String hostName){
    this.hostName = hostName;
}


public String getType(){
    return type;
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


public void setSn(String sn){
    this.sn = sn;
}


public void setSite(String site){
    this.site = site;
}


}