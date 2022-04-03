package com.example.steam.vo;
 import com.example.steam.entity.Image;
import com.example.steam.entity.User;
import org.springframework.stereotype.Component;
@Component
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
public void setCountry(String country){
    this.country = country;
}


public void setIntroduction(String introduction){
    this.introduction = introduction;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public String getCountry(){
    return country;
}


public void setProvince(String province){
    this.province = province;
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


public void setNickName(String nickName){
    this.nickName = nickName;
}


public void setIsAdmin(int isAdmin){
    this.isAdmin = isAdmin;
}


public void setBuyGames(int buyGames){
    this.buyGames = buyGames;
}


public void setEmail(String email){
    this.email = email;
}


public int getIsAdmin(){
    return isAdmin;
}


public void setCommentNum(int commentNum){
    this.commentNum = commentNum;
}


public String getProvince(){
    return province;
}


public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "[id=" + id + ",email=" + email + "]";
}


}