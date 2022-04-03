package com.example.steam.DTO;
 import org.springframework.stereotype.Component;
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


public Integer getGamePrice(){
    return gamePrice;
}


public Long getGameId(){
    return gameId;
}


public String getGameName(){
    return gameName;
}


public String getEmail(){
    return email;
}


public Long getId(){
    return id;
}


}