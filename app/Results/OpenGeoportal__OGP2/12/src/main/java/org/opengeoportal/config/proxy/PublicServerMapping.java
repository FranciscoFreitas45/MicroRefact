package org.opengeoportal.config.proxy;
 public class PublicServerMapping implements ServerMapping{

 private String type;

 private String externalUrl;


public void setExternalUrl(String externalUrl){
    this.externalUrl = externalUrl;
}


public String getType(){
    return type;
}


public String getExternalUrl(){
    return externalUrl;
}


public void setType(String type){
    this.type = type;
}


}