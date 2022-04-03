package com.gp.cricket.mapobject;
 public class PlayerRate {

 private  Integer playerId;

 private  String name;

 private  Double rate1;

 private  Double rate2;

 private  Double rate3;

public PlayerRate() {
    super();
// TODO Auto-generated constructor stub
}public PlayerRate(Integer playerId, String name, Double rate1, Double rate2, Double rate3) {
    super();
    this.playerId = playerId;
    this.name = name;
    this.rate1 = rate1;
    this.rate2 = rate2;
    this.rate3 = rate3;
}
public void setName(String name){
    this.name = name;
}


public void setRate2(Double rate2){
    this.rate2 = rate2;
}


public void setRate3(Double rate3){
    this.rate3 = rate3;
}


public String getName(){
    return name;
}


public void setPlayerId(Integer playerId){
    this.playerId = playerId;
}


public Double getRate1(){
    return rate1;
}


@Override
public String toString(){
    return "PlayerRate [playerId=" + playerId + ", name=" + name + ", rate1=" + rate1 + ", rate2=" + rate2 + ", rate3=" + rate3 + "]";
}


public Double getRate3(){
    return rate3;
}


public Double getRate2(){
    return rate2;
}


public Integer getPlayerId(){
    return playerId;
}


public void setRate1(Double rate1){
    this.rate1 = rate1;
}


}