package com.example.steam.vo;
 import com.example.steam.dao.RecentGameDao;
import com.example.steam.entity.RecentGame;
public class RecentGameVo extends RecentGame{

 private  String posterImage;

 private  String gameName;

public RecentGameVo() {
    super();
}
public String getPosterImage(){
    return posterImage;
}


public void setPosterImage(String posterImage){
    this.posterImage = posterImage;
}


public void setGameName(String gameName){
    this.gameName = gameName;
}


public String getGameName(){
    return gameName;
}


}