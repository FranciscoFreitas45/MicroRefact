package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class GetClientDTO {

 private  long id;

@JsonProperty("client_id")
 private  String clientId;

@JsonProperty("client_secret")
 private  String clientSecret;

@JsonProperty("redirect_uri")
 private  String redirectUri;

 private  String name;

 private  String description;

 private  String homepage;


public void setClientSecret(String clientSecret){
    this.clientSecret = clientSecret;
}


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


public String getClientSecret(){
    return clientSecret;
}


public String getHomepage(){
    return homepage;
}


public long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setHomepage(String homepage){
    this.homepage = homepage;
}


public void setId(long id){
    this.id = id;
}


public String getRedirectUri(){
    return redirectUri;
}


public void setClientId(String clientId){
    this.clientId = clientId;
}


}