package com.cg.hbm.NEW;
import org.springframework.stereotype.Service;
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.dao.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HotelRoomDetailsService {
 @Autowired

 private IHotelRepository ihotelrepository;


public Hotel getHotel(int hotel_id){
return ihotelrepository.getHotel(hotel_id);
}


public void setHotel(int hotel_id,Hotel hotel){
ihotelrepository.setHotel(hotel_id,hotel);
}


}