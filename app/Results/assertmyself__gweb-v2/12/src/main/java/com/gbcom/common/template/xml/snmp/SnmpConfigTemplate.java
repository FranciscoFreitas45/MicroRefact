package com.gbcom.common.template.xml.snmp;
 import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("SnmpConfig")
public class SnmpConfigTemplate {

@XStreamAlias("vendor")
 private  String vendor;

@XStreamAlias("defaultMib")
 private  String defaultMib;

@XStreamAlias("heartCacheKey")
 private  String heartCacheKey;

@XStreamAlias("rootOID")
 private  String rootOID;

@XStreamAlias("community")
 private  String community;


public void setVendor(String vendor){
    this.vendor = vendor;
}


public String getCommunity(){
    return community;
}


public void setCommunity(String community){
    this.community = community;
}


public void setRootOID(String rootOID){
    this.rootOID = rootOID;
}


public String getDefaultMib(){
    return defaultMib;
}


public void setHeartCacheKey(String heartCacheKey){
    this.heartCacheKey = heartCacheKey;
}


public String getVendor(){
    return vendor;
}


public String getRootOID(){
    return rootOID;
}


public void setDefaultMib(String defaultMib){
    this.defaultMib = defaultMib;
}


public String getHeartCacheKey(){
    return heartCacheKey;
}


}