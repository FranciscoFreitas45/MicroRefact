package com.cg.hbm.DTO;
 import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class RoomDetails {

 private  int room_id;

 private  String room_no;

 private  String room_type;

 private  double rate_per_day;

 private  boolean isAvailable;

 private  Hotel hotel;

public RoomDetails() {
}/**
 * @param room_no
 * @param rate_per_day
 * @param room_type
 * @param isAvailable
 * @param hotel
 */
public RoomDetails(String room_no, double rate_per_day, String room_type, boolean isAvailable, Hotel hotel) {
    super();
    this.room_no = room_no;
    this.room_type = room_type;
    this.rate_per_day = rate_per_day;
    this.isAvailable = isAvailable;
    this.hotel = hotel;
}
public double getRate_per_day(){
    return rate_per_day;
}


public String getRoom_no(){
    return room_no;
}


public int getRoom_id(){
    return room_id;
}


public Hotel getHotel(){
    return hotel;
}


public String getRoom_type(){
    return room_type;
}


}