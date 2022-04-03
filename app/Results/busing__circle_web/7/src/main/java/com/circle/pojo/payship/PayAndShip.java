package com.circle.pojo.payship;
 public class PayAndShip {

 private  int id;

 private  int userId;

 private  int payType;

 private  int shipType;


public int getPayType(){
    return payType;
}


public int getShipType(){
    return shipType;
}


public void setShipType(int shipType){
    this.shipType = shipType;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


public void setPayType(int payType){
    this.payType = payType;
}


}