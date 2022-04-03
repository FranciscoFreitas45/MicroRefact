package com.example.steam.DTO;
 import org.springframework.stereotype.Component;
import java.util.Date;
public class Game {

 public  int ISSUED_PUBLISH;

 public  int ISSUED_UNPUBLISH;

 private  Long id;

 private  String gameName;

 private  String gameIntroduction;

 private  String gameAbout;

 private  int issuedStatu;

 private  int gamePrice;

 private  Date issuedDate;

 private  Long posterImage;

 private  Long lowestSystem;

 private  Long recommendSystem;

 private  int sellNum;

 private  int discount;

public Game() {
}public Game(String gameName, String gameIntroduction, String gameAbout, int gamePrice, long lowestSystem, long recommendSystem, int discount) {
    this.gameName = gameName;
    this.gameIntroduction = gameIntroduction;
    this.gameAbout = gameAbout;
    this.gamePrice = gamePrice;
    this.lowestSystem = lowestSystem;
    this.recommendSystem = recommendSystem;
    this.discount = discount;
    this.issuedStatu = 0;
    this.issuedDate = new Date();
    this.sellNum = 0;
    this.posterImage = 1L;
}
public Long getPosterImage(){
    return posterImage;
}


public int getGamePrice(){
    return gamePrice;
}


public int getSellNum(){
    return sellNum;
}


public Long getId(){
    return id;
}


public int getIssuedStatu(){
    return issuedStatu;
}


public String getGameAbout(){
    return gameAbout;
}


public int getDiscount(){
    return discount;
}


public Long getRecommendSystem(){
    return recommendSystem;
}


public Date getIssuedDate(){
    return issuedDate;
}


public String getGameName(){
    return gameName;
}


public Long getLowestSystem(){
    return lowestSystem;
}


public String getGameIntroduction(){
    return gameIntroduction;
}


}