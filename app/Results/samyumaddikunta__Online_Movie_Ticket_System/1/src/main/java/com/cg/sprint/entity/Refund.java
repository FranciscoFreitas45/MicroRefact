package com.cg.sprint.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "refund")
public class Refund {

@Id
@Column(length = 5)
@GeneratedValue
 private  int sno;

@Column(length = 4)
 private  int account_no;

@Column(length = 5)
 private  int money_refunded;

@Column(length = 10)
 private  int booking_id;

@Column(length = 10)
 private  String date_of_refund;


public void setSno(int sno){
    this.sno = sno;
}


public int getMoney_refunded(){
    return money_refunded;
}


public String getDate_of_refund(){
    return date_of_refund;
}


public int getBooking_id(){
    return booking_id;
}


public void setBooking_id(int booking_id){
    this.booking_id = booking_id;
}


public int getSno(){
    return sno;
}


public void setAccount_no(int account_no){
    this.account_no = account_no;
}


public int getAccount_no(){
    return account_no;
}


public void setDate_of_refund(String date_of_refund){
    this.date_of_refund = date_of_refund;
}


public void setMoney_refunded(int money_refunded){
    this.money_refunded = money_refunded;
}


}