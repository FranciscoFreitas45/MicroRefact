package com.ushahidi.swiftriver.core.api.dto.CreateDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class Identity {

 private  String name;

 private  String username;

 private  String avatar;

@JsonProperty("origin_id")
 private  String originId;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public String getAvatar(){
    return avatar;
}


public void setOriginId(String originId){
    this.originId = originId;
}


public String getOriginId(){
    return originId;
}


public String getUsername(){
    return username;
}


}