package com.cg.hbm.DTO;
 import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.cg.hbm.Request.UserRequest;
import com.cg.hbm.Request.Impl.UserRequestImpl;
import com.cg.hbm.DTO.User;
import com.cg.hbm.Request.HotelRequest;
import com.cg.hbm.Request.Impl.HotelRequestImpl;
import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.Request.RoomDetailsRequest;
import com.cg.hbm.Request.Impl.RoomDetailsRequestImpl;
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
  this.user = userrequest.getUser(this.user_id);
return this.user;
}


public Hotel getHotel(){
  this.hotel = hotelrequest.getHotel(this.hotel_id);
return this.hotel;
}


public Date getBooked_to(){
    return booked_to;
}


public RoomDetails getRoomdetails(){
  this.roomdetails = roomdetailsrequest.getRoomdetails(this.room_id);
return this.roomdetails;
}


public int getNo_of_adults(){
    return no_of_adults;
}


public double getAmount(){
    return amount;
}


}