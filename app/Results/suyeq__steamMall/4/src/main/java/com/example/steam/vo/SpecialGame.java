package com.example.steam.vo;
 public class SpecialGame {

 private  Long id;

 private  String gameName;

 private  String posterImage;

 private  Integer discount;

 private  Integer gamePrice;

 private  Integer issuedStatu;

 private  String imageIntro1;

 private  String imageIntro2;

 private  String imageIntro3;

 private  String imageIntro4;

public SpecialGame() {
}
public String getPosterImage(){
    return posterImage;
}


public void setImageIntro4(String imageIntro4){
    this.imageIntro4 = imageIntro4;
}


public void setImageIntro3(String imageIntro3){
    this.imageIntro3 = imageIntro3;
}


public void setGameName(String gameName){
    this.gameName = gameName;
}


public void setIssuedStatu(Integer issuedStatu){
    this.issuedStatu = issuedStatu;
}


public Integer getGamePrice(){
    return gamePrice;
}


public Long getId(){
    return id;
}


public Integer getIssuedStatu(){
    return issuedStatu;
}


public void setGamePrice(Integer gamePrice){
    this.gamePrice = gamePrice;
}


public String getImageIntro2(){
    return imageIntro2;
}


public void setPosterImage(String posterImage){
    this.posterImage = posterImage;
}


public String getImageIntro1(){
    return imageIntro1;
}


public Integer getDiscount(){
    return discount;
}


public String getImageIntro4(){
    return imageIntro4;
}


@Override
public int hashCode(){
    return Integer.parseInt(getId() + "");
}


public String getGameName(){
    return gameName;
}


public String getImageIntro3(){
    return imageIntro3;
}


@Override
public boolean equals(Object o){
    if (o == this || o == null) {
        return false;
    }
    if (o.getClass() != this.getClass()) {
        return false;
    }
    GameDetail gameDetail = (GameDetail) o;
    if (gameDetail.getId().equals(getId())) {
        return true;
    }
    return false;
}


public void setDiscount(Integer discount){
    this.discount = discount;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return getId() + "";
}


public void setImageIntro2(String imageIntro2){
    this.imageIntro2 = imageIntro2;
}


public void setImageIntro1(String imageIntro1){
    this.imageIntro1 = imageIntro1;
}


}