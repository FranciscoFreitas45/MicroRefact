package com.example.steam.vo;
 import com.example.steam.entity.ShoppingCart;
public class ShoppingCartDetail extends ShoppingCart{

 private  String gamePoster;

public ShoppingCartDetail() {
}
public String getGamePoster(){
    return gamePoster;
}


public void setGamePoster(String gamePoster){
    this.gamePoster = gamePoster;
}


}