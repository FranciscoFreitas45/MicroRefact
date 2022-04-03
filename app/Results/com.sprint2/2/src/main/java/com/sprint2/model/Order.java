package com.sprint2.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
// defining class name as Table name
@Table(name = "Forest_Order")
public class Order {

@Id
@GeneratedValue
 private  Integer orderNumber;

 private  String deliveryPlace;

 private  String deliveryDate;

 private  String quantity;

public Order() {
    super();
}public Order(Integer orderNumber, String deliveryPlace, String deliveryDate, String quantity) {
    super();
    this.orderNumber = orderNumber;
    this.deliveryPlace = deliveryPlace;
    this.deliveryDate = deliveryDate;
    this.quantity = quantity;
}public Order(String deliveryPlace, String deliveryDate, String quantity) {
    super();
    this.deliveryPlace = deliveryPlace;
    this.deliveryDate = deliveryDate;
    this.quantity = quantity;
}
public void setDeliveryPlace(String deliveryPlace){
    this.deliveryPlace = deliveryPlace;
}


public String getQuantity(){
    return quantity;
}


public String getDeliveryPlace(){
    return deliveryPlace;
}


public void setQuantity(String quantity){
    this.quantity = quantity;
}


public String getDeliveryDate(){
    return deliveryDate;
}


public void setOrderNumber(Integer orderNumber){
    this.orderNumber = orderNumber;
}


@Override
public String toString(){
    return "Order [orderNumber=" + orderNumber + ", deliveryPlace=" + deliveryPlace + ", deliveryDate=" + deliveryDate + ", quantity=" + quantity + "]";
}


public Integer getOrderNumber(){
    return orderNumber;
}


public void setDeliveryDate(String deliveryDate){
    this.deliveryDate = deliveryDate;
}


}