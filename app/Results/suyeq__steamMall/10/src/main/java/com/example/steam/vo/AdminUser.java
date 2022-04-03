package com.example.steam.vo;
 import org.springframework.stereotype.Component;
@Component
public class AdminUser {

 private  Long id;

 private  String nickName;

 private  String email;

public AdminUser() {
}
public void setEmail(String email){
    this.email = email;
}


public String getNickName(){
    return nickName;
}


public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public Long getId(){
    return id;
}


public void setNickName(String nickName){
    this.nickName = nickName;
}


@Override
public String toString(){
    return "[id=" + id + ",email=" + email + ",nickName=" + nickName + "]";
}


}