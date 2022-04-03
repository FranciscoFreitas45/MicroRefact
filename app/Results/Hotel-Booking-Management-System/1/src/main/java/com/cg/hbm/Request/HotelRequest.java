package com.cg.hbm.Request;
import com.cg.hbm.DTO.Hotel;
public interface HotelRequest {

   public void setHotel(Hotel hotel,int hotel_id);
   public Hotel getHotel(int hotel_id);
}