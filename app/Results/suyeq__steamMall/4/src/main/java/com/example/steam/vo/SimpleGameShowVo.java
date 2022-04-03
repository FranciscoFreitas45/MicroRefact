package com.example.steam.vo;
 public class SimpleGameShowVo {

 private  long gameId;

 private  String gameName;

 private  String posterImage;

 private  int playTime;

public SimpleGameShowVo() {
}public SimpleGameShowVo(long gameId, String gameName, String posterImage, int playTime) {
    this.gameId = gameId;
    this.gameName = gameName;
    this.posterImage = posterImage;
    this.playTime = playTime;
}
public String getPosterImage(){
    return posterImage;
}


public void setPosterImage(String posterImage){
    this.posterImage = posterImage;
}


public int getPlayTime(){
    return playTime;
}


public void setGameName(String gameName){
    this.gameName = gameName;
}


public void setGameId(long gameId){
    this.gameId = gameId;
}


public long getGameId(){
    return gameId;
}


public String getGameName(){
    return gameName;
}


public void setPlayTime(int playTime){
    this.playTime = playTime;
}


}