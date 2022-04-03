package com.circle.pojo.order;
 public class OrderDetail {

 private  int id;

 private  String orderId;

 private  String goodName;

 private  String goodTitle;

 private  int goodUnit;

 private  String goodId;

 private  String unitPrice;

 private  int buyNum;

 private  double total;


public void setTotal(double total){
    this.total = total;
}


public String getGoodId(){
    return goodId;
}


public void setGoodId(String goodId){
    this.goodId = goodId;
}


public void setUnitPrice(String unitPrice){
    this.unitPrice = unitPrice;
}


public void setGoodName(String goodName){
    this.goodName = goodName;
}


public String getGoodTitle(){
    return goodTitle;
}


public String getOrderId(){
    return orderId;
}


public String getGoodName(){
    return goodName;
}


public int getId(){
    return id;
}


public void setOrderId(String orderId){
    this.orderId = orderId;
}


public void setGoodTitle(String goodTitle){
    this.goodTitle = goodTitle;
}


public int getBuyNum(){
    return buyNum;
}


public int getGoodUnit(){
    return goodUnit;
}


public void setGoodUnit(int goodUnit){
    this.goodUnit = goodUnit;
}


public void setBuyNum(int buyNum){
    this.buyNum = buyNum;
}


public void setId(int id){
    this.id = id;
}


public double getTotal(){
    return total;
}


public String getUnitPrice(){
    return unitPrice;
}


}