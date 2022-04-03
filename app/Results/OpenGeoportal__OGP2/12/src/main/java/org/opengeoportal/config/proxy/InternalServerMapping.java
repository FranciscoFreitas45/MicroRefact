package org.opengeoportal.config.proxy;
 public class InternalServerMapping implements ServerMapping{

 private String type;

 private String internalUrl;

 private String externalUrl;

 private String username;

 private String password;


public void setPassword(String password){
    this.password = password;
}


public String getPassword(){
    return password;
}


public String getInternalUrl(){
    return internalUrl;
}


public void setExternalUrl(String externalUrl){
    this.externalUrl = externalUrl;
}


public void setUsername(String username){
    this.username = username;
}


public String getType(){
    return type;
}


public void setInternalUrl(String internalUrl){
    this.internalUrl = internalUrl;
}


public String getExternalUrl(){
    return externalUrl;
}


public void setType(String type){
    this.type = type;
}


public String getUsername(){
    return username;
}


}