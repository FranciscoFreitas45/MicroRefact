package com.example.steam.entity;
 import org.springframework.stereotype.Component;
@Component
public class ShoppingCart {

 private  Long id;

 private  String email;

 private  Long gameId;

 private  Long posterImage;

 private  Integer gamePrice;

 private  String gameName;

public ShoppingCart() {
}
public Long getPosterImage(){
    return posterImage;
}


public void setPosterImage(Long posterImage){
    this.posterImage = posterImage;
}


public void setGameName(String gameName){
    this.gameName = gameName;
}


public Integer getGamePrice(){
    return gamePrice;
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


public String getGameName(){
    return gameName;
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


public void setGamePrice(Integer gamePrice){
    this.gamePrice = gamePrice;
}


}