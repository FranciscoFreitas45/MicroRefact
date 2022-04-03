package com.example.steam.entity;
 import org.springframework.stereotype.Component;
import java.util.Date;
@Component
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


public void setGameName(String gameName){
    this.gameName = gameName;
}


public void setIssuedStatu(int issuedStatu){
    this.issuedStatu = issuedStatu;
}


public int getGamePrice(){
    return gamePrice;
}


public void setIssuedDate(Date issuedDate){
    this.issuedDate = issuedDate;
}


public void setGameAbout(String gameAbout){
    this.gameAbout = gameAbout;
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


public void setGamePrice(int gamePrice){
    this.gamePrice = gamePrice;
}


public String getGameAbout(){
    return gameAbout;
}


public void setPosterImage(Long posterImage){
    this.posterImage = posterImage;
}


public int getDiscount(){
    return discount;
}


public Long getRecommendSystem(){
    return recommendSystem;
}


public void setGameIntroduction(String gameIntroduction){
    this.gameIntroduction = gameIntroduction;
}


public Date getIssuedDate(){
    return issuedDate;
}


public void setLowestSystem(Long lowestSystem){
    this.lowestSystem = lowestSystem;
}


public String getGameName(){
    return gameName;
}


public void setDiscount(int discount){
    this.discount = discount;
}


public void setId(Long id){
    this.id = id;
}


public void setSellNum(int sellNum){
    this.sellNum = sellNum;
}


public Long getLowestSystem(){
    return lowestSystem;
}


public String getGameIntroduction(){
    return gameIntroduction;
}


public void setRecommendSystem(Long recommendSystem){
    this.recommendSystem = recommendSystem;
}


}