package com.cg.sprint.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "payments")
public class Payments {

@Id
@Column(length = 5)
@GeneratedValue
 private  int booking_id;

@Column(length = 4)
 private  int account_no;

@Column(length = 5)
 private  int money_collected;

@Column(length = 3)
 private  int seats_booked;

@Column(length = 10)
 private  String seat_type;

@Column(length = 5)
 private  int refund;

@Column(length = 10)
 private  String date_of_transac;


public void setSeats_booked(int seats_booked){
    this.seats_booked = seats_booked;
}


public int getBooking_id(){
    return booking_id;
}


public void setMoney_collected(int money_collected){
    this.money_collected = money_collected;
}


public void setSeat_type(String seat_type){
    this.seat_type = seat_type;
}


public int getMoney_collected(){
    return money_collected;
}


public void setDate_of_transac(String date_of_transac){
    this.date_of_transac = date_of_transac;
}


public void setRefund(int refund){
    this.refund = refund;
}


public int getRefund(){
    return refund;
}


public void setBooking_id(int booking_id){
    this.booking_id = booking_id;
}


public void setAccount_no(int account_no){
    this.account_no = account_no;
}


public int getAccount_no(){
    return account_no;
}


public String getDate_of_transac(){
    return date_of_transac;
}


public int getSeats_booked(){
    return seats_booked;
}


public String getSeat_type(){
    return seat_type;
}


}