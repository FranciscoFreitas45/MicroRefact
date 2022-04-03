package com.ushahidi.swiftriver.core.api.dto.AccountDTO;
 import org.codehaus.jackson.annotate.JsonProperty;
public class Owner {

 private  String avatar;

 private  String name;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public String getAvatar(){
    return avatar;
}


}