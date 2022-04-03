package com.ushahidi.swiftriver.core.api.dto.GetDropDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class Identity {

 private  long id;

 private  String name;

 private  String username;

 private  String avatar;


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


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public String getUsername(){
    return username;
}


}