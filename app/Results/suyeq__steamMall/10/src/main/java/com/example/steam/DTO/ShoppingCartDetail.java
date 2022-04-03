package com.example.steam.DTO;
 import com.example.steam.entity.ShoppingCart;
public class ShoppingCartDetail extends ShoppingCart{

 private  String gamePoster;

public ShoppingCartDetail() {
}
public String getGamePoster(){
    return gamePoster;
}


}