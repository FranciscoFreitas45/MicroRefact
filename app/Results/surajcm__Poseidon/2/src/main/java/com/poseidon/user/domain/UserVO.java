package com.poseidon.user.domain;
 import java.util.StringJoiner;
@SuppressWarnings("unused")
public class UserVO {

 private  Long id;

 private  String name;

 private  String email;

 private  String password;

 private  String role;

 private  Boolean expired;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public String getRole(){
    return role;
}


public Long getId(){
    return id;
}


public Boolean getExpired(){
    return expired;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setExpired(Boolean expired){
    this.expired = expired;
}


public void setRole(String role){
    this.role = role;
}


public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return new StringJoiner(", ", UserVO.class.getSimpleName() + "[", "]").add("id=" + id).add("name='" + name + "'").add("email='" + email + "'").add("role='" + role + "'").toString();
}


}