package com.cg.hbm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.entites.Hotel;
@RestController
@CrossOrigin
public class HotelBookingDetailsController {

@Autowired
 private HotelBookingDetailsService hotelbookingdetailsservice;


@PutMapping
("/BookingDetails/{id}/Hotel/setHotel")
public void setHotel(@PathVariable(name="id") int hotel_id,@RequestBody Hotel hotel){
hotelbookingdetailsservice.setHotel(hotel_id,hotel);
}


@GetMapping
("/BookingDetails/{id}/Hotel/getHotel")
public Hotel getHotel(@PathVariable(name="id") int hotel_id){
return hotelbookingdetailsservice.getHotel(hotel_id);
}


}