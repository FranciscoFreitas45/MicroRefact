package com.example.steam.entity;
 import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class RecentGame {

 private  Long id;

 private  String email;

 private  Long gameId;

 private  Date lastPlay;

 private  Integer playTime;

public RecentGame() {
}
public Integer getPlayTime(){
    return playTime;
}


public void setEmail(String email){
    this.email = email;
}


public void setGameId(Long gameId){
    this.gameId = gameId;
}


public Long getGameId(){
    return gameId;
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


public Date getLastPlay(){
    return lastPlay;
}


public void setPlayTime(Integer playTime){
    this.playTime = playTime;
}


public void setLastPlay(Date lastPlay){
    this.lastPlay = lastPlay;
}


}