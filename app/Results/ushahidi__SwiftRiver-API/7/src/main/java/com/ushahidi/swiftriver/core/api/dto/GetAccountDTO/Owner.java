package com.ushahidi.swiftriver.core.api.dto.GetAccountDTO;
 import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
public class Owner {

 private  String name;

 private  String email;

 private  String username;

 private  String avatar;

@JsonProperty("date_added")
 private  String dateCreated;

 private  Boolean active;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setActive(Boolean active){
    this.active = active;
}


public void setEmail(String email){
    this.email = email;
}


public void setUsername(String username){
    this.username = username;
}


public String getDateCreated(){
    return dateCreated;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public String getAvatar(){
    return avatar;
}


public String getEmail(){
    return email;
}


public Boolean getActive(){
    return active;
}


public void setDateCreated(String dateCreated){
    this.dateCreated = dateCreated;
}


public String getUsername(){
    return username;
}


}