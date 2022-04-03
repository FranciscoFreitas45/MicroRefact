package com.cg.hbm.service;
 import java.util.List;
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.entites.RoomDetails;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.exceptions.RoomDetailsNotFoundException;
public interface IHotelService {


public List<Hotel> showAllHotels()
;

public Hotel showHotel(int hotel_id)
;

public Hotel updateHotel(int hotelId,Hotel hotel)
;

public Hotel addHotel(Hotel hotel)
;

public Hotel removeHotel(int hotel_id)
;

}