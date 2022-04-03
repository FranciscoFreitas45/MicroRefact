package com.example.steam.DTO;
 import java.util.Date;
public class CommentDetail {

 private  Long id;

 private  Long userId;

 private  String nickName;

 private  String avatar;

 private  String email;

 private  double playTime;

 private  int commmentNum;

 private  int buyGames;

 private  String content;

 private  Date commentDate;

 private  Integer zanNum;

 private  Integer caiNum;

 private  Integer recommendStatu;

 private  Integer happyNum;

 private  long gameId;

public CommentDetail() {
}
public int getCommmentNum(){
    return commmentNum;
}


public double getPlayTime(){
    return playTime;
}


public void setHappyNum(Integer happyNum){
    this.happyNum = happyNum;
}


public Integer getZanNum(){
    return zanNum;
}


public String getNickName(){
    return nickName;
}


public int getBuyGames(){
    return buyGames;
}


public String getAvatar(){
    return avatar;
}


public String getContent(){
    return content;
}


public Date getCommentDate(){
    return commentDate;
}


public long getGameId(){
    return gameId;
}


public Long getId(){
    return id;
}


public void setCommmentNum(int commmentNum){
    this.commmentNum = commmentNum;
}


public void setZanNum(Integer zanNum){
    this.zanNum = zanNum;
}


public void setNickName(String nickName){
    this.nickName = nickName;
}


public Integer getHappyNum(){
    return happyNum;
}


public void setGameId(long gameId){
    this.gameId = gameId;
}


public Integer getRecommendStatu(){
    return recommendStatu;
}


public String getEmail(){
    return email;
}


public void setCaiNum(Integer caiNum){
    this.caiNum = caiNum;
}


public Long getUserId(){
    return userId;
}


public Integer getCaiNum(){
    return caiNum;
}


}