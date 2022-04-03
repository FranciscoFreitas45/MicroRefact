package com.example.steam.vo;
 import java.util.Date;
import java.util.List;
public class GameDetail extends SpecialGame{

 private  String imageIntro5;

 private  String gameIntroduction;

 private  String gameAbout;

 private  Date issuedDate;

 private  Integer sellNum;

 private  List<String> label;

 private  List<String> type;

 private  Long lowestSystem;

 private  Long recommendSystem;

public GameDetail() {
}
public void setImageIntro5(String imageIntro5){
    this.imageIntro5 = imageIntro5;
}


public List<String> getLabel(){
    return label;
}


public void setIssuedDate(Date issuedDate){
    this.issuedDate = issuedDate;
}


public void setGameAbout(String gameAbout){
    this.gameAbout = gameAbout;
}


public Integer getSellNum(){
    return sellNum;
}


public void setType(List<String> type){
    this.type = type;
}


public String getGameAbout(){
    return gameAbout;
}


public String getImageIntro5(){
    return imageIntro5;
}


public List<String> getType(){
    return type;
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


public void setLabel(List<String> label){
    this.label = label;
}


public void setSellNum(Integer sellNum){
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