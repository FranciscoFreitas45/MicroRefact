package com.cg.hbm.DTO;
 import java.util.Date;

import com.cg.hbm.DTO.RoomDetails;
public class BookingDetails {

 private  int booking_id;

 private  Date booked_from;

 private  Date booked_to;

 private  int no_of_adults;

 private  int no_of_children;

 private  double amount;

 private  User user;

 private  Hotel hotel;

 private  RoomDetails roomdetails;

 private int user_id;

 private int hotel_id;

 private int room_id;

public BookingDetails() {
}/**
 * @param booked_from
 * @param booked_to
 * @param no_of_adults
 * @param no_of_children
 * @param amount
 * @param hotel
 * @param roomdetails
 * @param user
 */
public BookingDetails(Date booked_from, Date booked_to, int no_of_adults, int no_of_children, double amount, Hotel hotel, RoomDetails roomdetails, User user) {
    super();
    // this.booking_id=booking_id;
    this.booked_from = booked_from;
    this.booked_to = booked_to;
    this.no_of_adults = no_of_adults;
    this.no_of_children = no_of_children;
    this.amount = amount;
    this.hotel = hotel;
    this.roomdetails = roomdetails;
    this.user = user;
}
public Date getBooked_from(){
    return booked_from;
}


public int getNo_of_children(){
    return no_of_children;
}


public int getBooking_id(){
    return booking_id;
}


public User getUser(){
return this.user;
}


public Hotel getHotel(){
return this.hotel;
}


public Date getBooked_to(){
    return booked_to;
}


public RoomDetails getRoomdetails(){
return this.roomdetails;
}


public int getNo_of_adults(){
    return no_of_adults;
}


public double getAmount(){
    return amount;
}

public void setBooking_Id(int id){
    this.booking_id = id;
}


}