package com.cg.hbm.NEW;
import org.springframework.web.bind.annotation.*;
import com.cg.hbm.entites.Hotel;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@CrossOrigin
public class HotelRoomDetailsController {
@Autowired

 private HotelRoomDetailsService hotelroomdetailsservice;


@GetMapping
("/RoomDetails/{id}/Hotel/getHotel")
public Hotel getHotel(@PathVariable(name="id") int hotel_id){
System.out.println(hotel_id);
return hotelroomdetailsservice.getHotel(hotel_id);
}


@PutMapping
("/RoomDetails/{id}/Hotel/setHotel")
public void setHotel(@PathVariable(name="id") int hotel_id,@RequestBody Hotel hotel){
System.out.println("set em " +hotel_id);
hotelroomdetailsservice.setHotel(hotel_id,hotel);
}


}
