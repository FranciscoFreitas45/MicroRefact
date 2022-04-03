package com.sobey.cmop.mvc.poi.port;
 public class Port {

 private  String name;

 private  String ipAddress;

 private  String macAddress;

 private  String site;

 private  String connectedTo;

 private  String hardware;


public void setName(String name){
    this.name = name;
}


public void setHardware(String hardware){
    this.hardware = hardware;
}


public String getConnectedTo(){
    return connectedTo;
}


public String getName(){
    return name;
}


public String getMacAddress(){
    return macAddress;
}


public String getIpAddress(){
    return ipAddress;
}


public void setConnectedTo(String connectedTo){
    this.connectedTo = connectedTo;
}


public String getSite(){
    return site;
}


public void setSite(String site){
    this.site = site;
}


public void setMacAddress(String macAddress){
    this.macAddress = macAddress;
}


public String getHardware(){
    return hardware;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


}