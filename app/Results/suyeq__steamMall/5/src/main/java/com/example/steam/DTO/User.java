package com.example.steam.DTO;
 import org.springframework.stereotype.Component;
public class User {

 public  int ISADMIN;

 public  int NOADMIN;

 private  Long id;

 private  String nickName;

 private  String salt;

 private  String email;

 private  String password;

 private  Long avatar;

 private  double playTime;

 private  int commentNum;

 private  int buyGames;

 private  int isAdmin;

 private  int lv;

 private  String country;

 private  String province;

 private  String introduction;

public User() {
}public User(String nickName, String email, String salt, String password) {
    this.nickName = nickName;
    this.email = email;
    this.salt = salt;
    this.password = password;
    this.avatar = 1L;
    this.playTime = 0;
    this.buyGames = 0;
    this.commentNum = 0;
    this.isAdmin = 0;
    this.lv = 0;
    this.country = "湖南";
    this.province = "长沙";
    this.introduction = "这个人很懒，什么都没写~";
}public User(String nickName, String email, String salt, String password, long avatar, String province, String introduction, int isAdmin) {
    this.nickName = nickName;
    this.email = email;
    this.salt = salt;
    this.password = password;
    this.avatar = avatar;
    this.playTime = 0;
    this.buyGames = 0;
    this.commentNum = 0;
    this.isAdmin = isAdmin;
    this.lv = 0;
    this.country = "湖南";
    this.province = province;
    this.introduction = introduction;
}public User(long id, String nickName, String salt, String email, String password, long avatar, int playTime, int buyGames, int commentNum, int isAdmin, int lv, String country, String province, String introduction) {
    this.id = id;
    this.nickName = nickName;
    this.email = email;
    this.salt = salt;
    this.password = password;
    this.avatar = avatar;
    this.playTime = playTime;
    this.buyGames = buyGames;
    this.commentNum = commentNum;
    this.isAdmin = isAdmin;
    this.lv = lv;
    this.country = country;
    this.province = province;
    this.introduction = introduction;
}
public double getPlayTime(){
    return playTime;
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


public Long getAvatar(){
    return avatar;
}


public int getBuyGames(){
    return buyGames;
}


public String getSalt(){
    return salt;
}


public Long getId(){
    return id;
}


public String getProvince(){
    return province;
}


public int getLv(){
    return lv;
}


public String getIntroduction(){
    return introduction;
}


public String getPassword(){
    return password;
}


public int getIsAdmin(){
    return isAdmin;
}


public String getEmail(){
    return email;
}


}