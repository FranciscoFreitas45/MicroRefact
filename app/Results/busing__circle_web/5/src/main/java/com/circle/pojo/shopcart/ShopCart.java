package com.circle.pojo.shopcart;
 public class ShopCart {

 private  int cartId;

 private  int goodId;

 private  int circleId;

 private  int buyNum;

 private  int unitId;

 private  String sessionId;

 private  int userId;

 private  double price;

 private  double total;

 private  String addTime;

 private  String overTime;

 private  int batchId;


public void setTotal(double total){
    this.total = total;
}


public int getCartId(){
    return cartId;
}


public int getGoodId(){
    return goodId;
}


public void setAddTime(String addTime){
    this.addTime = addTime;
}


public void setGoodId(int goodId){
    this.goodId = goodId;
}


public int getUnitId(){
    return unitId;
}


public int getCircleId(){
    return circleId;
}


public void setBatchId(int batchId){
    this.batchId = batchId;
}


public void setPrice(double price){
    this.price = price;
}


public int getBuyNum(){
    return buyNum;
}


public void setCircleId(int circleId){
    this.circleId = circleId;
}


public double getPrice(){
    return price;
}


public String getAddTime(){
    return addTime;
}


public void setBuyNum(int buyNum){
    this.buyNum = buyNum;
}


public void setUnitId(int unitId){
    this.unitId = unitId;
}


public void setSessionId(String sessionId){
    this.sessionId = sessionId;
}


public String getOverTime(){
    return overTime;
}


public void setCartId(int cartId){
    this.cartId = cartId;
}


public int getBatchId(){
    return batchId;
}


public double getTotal(){
    return total;
}


public String getSessionId(){
    return sessionId;
}


public int getUserId(){
    return userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


public void setOverTime(String overTime){
    this.overTime = overTime;
}


}