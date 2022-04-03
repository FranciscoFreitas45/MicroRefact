package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class GameImage {

 private  Long id;

 private  Long gameId;

 private  Long image1;

 private  Long image2;

 private  Long image3;

 private  Long image4;

 private  Long image5;

public GameImage() {
}public GameImage(long gameId, long image1, long image2, long image3, long image4, long image5) {
    this.gameId = gameId;
    this.image1 = image1;
    this.image2 = image2;
    this.image3 = image3;
    this.image4 = image4;
    this.image5 = image5;
}
public Long getImage5(){
    return image5;
}


public Long getGameId(){
    return gameId;
}


public Long getId(){
    return id;
}


public void setGameId(Long gameId){
    this.gameId = gameId;
}


public void setId(Long id){
    this.id = id;
}


public void setImage5(Long image5){
    this.image5 = image5;
}


public Long getImage2(){
    return image2;
}


public void setImage3(Long image3){
    this.image3 = image3;
}


public Long getImage1(){
    return image1;
}


public void setImage4(Long image4){
    this.image4 = image4;
}


public void setImage1(Long image1){
    this.image1 = image1;
}


public Long getImage4(){
    return image4;
}


public void setImage2(Long image2){
    this.image2 = image2;
}


public Long getImage3(){
    return image3;
}


}