package com.optimize.chapter2.valueObject;
 import java.io.Serializable;
public class Order implements Serializable{

 private  long serialVersionUID;

 private  int orderId;

 private  String clientName;

 private  int number;

 private  String productName;


public void setClientName(String clientName){
    this.clientName = clientName;
}


public int getNumber(){
    return number;
}


public void setProductName(String productName){
    this.productName = productName;
}


public int getOrderId(){
    return orderId;
}


public void setOrderId(int orderId){
    this.orderId = orderId;
}


public void setNumber(int number){
    this.number = number;
}


public String getProductName(){
    return productName;
}


public String getClientName(){
    return clientName;
}


}