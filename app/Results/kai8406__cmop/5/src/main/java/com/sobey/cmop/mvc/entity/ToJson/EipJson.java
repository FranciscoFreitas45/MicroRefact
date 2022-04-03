package com.sobey.cmop.mvc.entity.ToJson;
 public class EipJson {

 private  Integer id;

 private  String identifier;

 private  String ispType;

 private  String ipAddress;

 private  String oldIp;

 private  Integer linkType;

 private  String link;


public String getIspType(){
    return ispType;
}


public void setIspType(String ispType){
    this.ispType = ispType;
}


public Integer getLinkType(){
    return linkType;
}


public String getIpAddress(){
    return ipAddress;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public Integer getId(){
    return id;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setLink(String link){
    this.link = link;
}


public String getLink(){
    return link;
}


public String getOldIp(){
    return oldIp;
}


public String getIdentifier(){
    return identifier;
}


public void setOldIp(String oldIp){
    this.oldIp = oldIp;
}


public void setLinkType(Integer linkType){
    this.linkType = linkType;
}


public void setId(Integer id){
    this.id = id;
}


}