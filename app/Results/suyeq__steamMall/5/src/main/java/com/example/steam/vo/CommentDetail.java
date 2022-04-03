package com.example.steam.vo;
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


public void setContent(String content){
    this.content = content;
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


public void setBuyGames(int buyGames){
    this.buyGames = buyGames;
}


public void setCommmentNum(int commmentNum){
    this.commmentNum = commmentNum;
}


public void setId(Long id){
    this.id = id;
}


public void setZanNum(Integer zanNum){
    this.zanNum = zanNum;
}


public void setAvatar(String avatar){
    this.avatar = avatar;
}


public void setNickName(String nickName){
    this.nickName = nickName;
}


public void setPlayTime(double playTime){
    this.playTime = playTime;
}


public Integer getHappyNum(){
    return happyNum;
}


public void setEmail(String email){
    this.email = email;
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


public void setCommentDate(Date commentDate){
    this.commentDate = commentDate;
}


public void setCaiNum(Integer caiNum){
    this.caiNum = caiNum;
}


public void setRecommendStatu(Integer recommendStatu){
    this.recommendStatu = recommendStatu;
}


public Long getUserId(){
    return userId;
}


public Integer getCaiNum(){
    return caiNum;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}