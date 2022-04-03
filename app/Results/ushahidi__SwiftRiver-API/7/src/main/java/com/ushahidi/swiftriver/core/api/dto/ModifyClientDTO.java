package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class ModifyClientDTO {

@JsonProperty("client_id")
 private  String clientId;

 private  String name;

@JsonProperty("redirect_uri")
 private  String redirectUri;

 private  String description;

 private  String homepage;


public void setName(String name){
    this.name = name;
}


public void setRedirectUri(String redirectUri){
    this.redirectUri = redirectUri;
}


public String getClientId(){
    return clientId;
}


public String getName(){
    return name;
}


public void setHomepage(String homepage){
    this.homepage = homepage;
}


public String getHomepage(){
    return homepage;
}


public void setDescription(String description){
    this.description = description;
}


public String getRedirectUri(){
    return redirectUri;
}


public String getDescription(){
    return description;
}


public void setClientId(String clientId){
    this.clientId = clientId;
}


}