package com.ushahidi.swiftriver.core.api.dto;
 import org.codehaus.jackson.annotate.JsonProperty;
public class AccountDTO {

 private  long id;

@JsonProperty("account_path")
 private  String accountPath;

 private  Owner owner;

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


public String getAccountPath(){
    return accountPath;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


public Owner getOwner(){
    return owner;
}


public void setOwner(Owner owner){
    this.owner = owner;
}


}