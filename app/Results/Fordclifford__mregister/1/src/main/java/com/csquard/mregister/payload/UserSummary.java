package com.csquard.mregister.payload;
 public class UserSummary {

 private  Long id;

 private  String username;

 private  String name;

public UserSummary(Long id, String username, String name) {
    this.id = id;
    this.username = username;
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public String getUsername(){
    return username;
}


}