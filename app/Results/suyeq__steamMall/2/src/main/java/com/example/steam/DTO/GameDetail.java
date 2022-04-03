package com.example.steam.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

public GameDetail() {
}
public List<String> getLabel(){
    return label;
}


public Integer getSellNum(){
    return sellNum;
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


public Date getIssuedDate(){
    return issuedDate;
}


public Long getLowestSystem(){
    return lowestSystem;
}


public String getGameIntroduction(){
    return gameIntroduction;
}


public void setImageIntro5(String imageIntro5){
    this.imageIntro5 = imageIntro5;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setImageIntro5"))

.queryParam("imageIntro5",imageIntro5)
;
restTemplate.put(builder.toUriString(),null);
}


}