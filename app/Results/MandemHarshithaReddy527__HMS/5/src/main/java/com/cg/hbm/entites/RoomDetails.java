package com.cg.hbm.entites;
 import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.cg.hbm.Request.HotelRequest;
import com.cg.hbm.Request.Impl.HotelRequestImpl;
import com.cg.hbm.DTO.Hotel;
@Entity
@Table(name = "room_details")
public class RoomDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int room_id;

 private  String room_no;

 private  String room_type;

 private  double rate_per_day;

 private  boolean isAvailable;

@Transient
 private  Hotel hotel;

@Column(name = "hotel_id")
 private int hotel_id;

@Transient
 private HotelRequest hotelrequest = new HotelRequestImpl();;

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
public void setRate_per_day(double rate_per_day){
    this.rate_per_day = rate_per_day;
}


public boolean isAvailable(){
    return isAvailable;
}


public double getRate_per_day(){
    return rate_per_day;
}


public void setRoom_id(int room_id){
    this.room_id = room_id;
}


public String getRoom_no(){
    return room_no;
}


public int getRoom_id(){
    return room_id;
}


public void setAvailable(boolean isAvailable){
    this.isAvailable = isAvailable;
}


public Hotel getHotel(){
  this.hotel = hotelrequest.getHotel(this.hotel_id);
return this.hotel;
}


public String getRoom_type(){
    return room_type;
}


public void setHotel(Hotel hotel){
 hotelrequest.setHotel(hotel,this.hotel_id);
}



public void setRoom_no(String room_no){
    this.room_no = room_no;
}


public void setRoom_type(String room_type){
    this.room_type = room_type;
}


}