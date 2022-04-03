package com.example.steam.DTO;
 import com.example.steam.entity.Image;
import com.example.steam.entity.User;
import org.springframework.stereotype.Component;
public class LoginUser {

 private  Long id;

 private  String nickName;

 private  String email;

 private  String avatar;

 private  int isAdmin;

 private  String country;

 private  String province;

 private  String introduction;

 private  int commentNum;

 private  int buyGames;

public LoginUser() {
}public LoginUser(User user, Image image) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.nickName = user.getNickName();
    this.isAdmin = user.getIsAdmin();
    this.avatar = image.getUrl();
}
public String getCountry(){
    return country;
}


public int getCommentNum(){
    return commentNum;
}


public String getNickName(){
    return nickName;
}


public String getAvatar(){
    return avatar;
}


public String getIntroduction(){
    return introduction;
}


public int getBuyGames(){
    return buyGames;
}


public Long getId(){
    return id;
}


public int getIsAdmin(){
    return isAdmin;
}


public String getProvince(){
    return province;
}


public String getEmail(){
    return email;
}


}