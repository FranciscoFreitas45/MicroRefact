package com.sobey.cmop.mvc.entity.ToJson;
 public class DnsJson {

 private  Integer id;

 private  String identifier;

 private  String domainName;

 private  String domainType;

 private  String cnameDomain;

 private  String targetEip;


public void setDomainType(String domainType){
    this.domainType = domainType;
}


public String getTargetEip(){
    return targetEip;
}


public String getIdentifier(){
    return identifier;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setCnameDomain(String cnameDomain){
    this.cnameDomain = cnameDomain;
}


public String getDomainType(){
    return domainType;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public String getDomainName(){
    return domainName;
}


public void setDomainName(String domainName){
    this.domainName = domainName;
}


public void setTargetEip(String targetEip){
    this.targetEip = targetEip;
}


public String getCnameDomain(){
    return cnameDomain;
}


}