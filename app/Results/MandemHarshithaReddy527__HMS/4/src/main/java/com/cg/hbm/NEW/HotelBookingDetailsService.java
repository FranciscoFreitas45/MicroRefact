package com.cg.hbm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.dao.IHotelRepository;
import com.cg.hbm.entites.Hotel;
@Service
public class HotelBookingDetailsService {

@Autowired
 private IHotelRepository ihotelrepository;


public void setHotel(int hotel_id,Hotel hotel){
ihotelrepository.setHotel(hotel_id,hotel);
}


public Hotel getHotel(int hotel_id){
return ihotelrepository.getHotel(hotel_id);
}


}