package com.example.steam.entity;
 import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class Comment {

 private  Long id;

 private  String content;

 private  Date commentDate;

 private  String email;

 private  Long gameId;

 private  Integer zanNum;

 private  Integer caiNum;

 private  Integer recommendStatu;

 private  Integer happy;

public Comment() {
}public Comment(String content, String email, long gameId, int recommendStatu) {
    this.content = content;
    this.commentDate = new Date();
    this.email = email;
    this.gameId = gameId;
    this.recommendStatu = recommendStatu;
    this.zanNum = 0;
    this.caiNum = 0;
    this.happy = 0;
}
public void setContent(String content){
    this.content = content;
}


public void setZanNum(Integer zanNum){
    this.zanNum = zanNum;
}


public Integer getZanNum(){
    return zanNum;
}


public String getContent(){
    return content;
}


public Date getCommentDate(){
    return commentDate;
}


public Long getGameId(){
    return gameId;
}


public Long getId(){
    return id;
}


public void setHappy(Integer happy){
    this.happy = happy;
}


public Integer getHappy(){
    return happy;
}


public void setEmail(String email){
    this.email = email;
}


public void setGameId(Long gameId){
    this.gameId = gameId;
}


public void setId(Long id){
    this.id = id;
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


public Integer getCaiNum(){
    return caiNum;
}


}