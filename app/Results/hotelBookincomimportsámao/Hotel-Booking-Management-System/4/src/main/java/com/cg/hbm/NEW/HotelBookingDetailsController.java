package com.cg.hbm.NEW;
import org.springframework.web.bind.annotation.*;
import com.cg.hbm.entites.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;



@RestController
@CrossOrigin
public class HotelBookingDetailsController {
@Autowired
 private HotelBookingDetailsService hotelbookingdetailsservice;


@PutMapping
("/BookingDetails/{id}/Hotel/setHotel")
public void setHotel(@PathVariable(name="id") int hotel_id,@RequestBody Hotel hotel){
    System.out.println("RECEBI PEDIDO COM ID " + hotel_id);
     System.out.println("E BODY " +hotel);
hotelbookingdetailsservice.setHotel(hotel_id,hotel);
}


@GetMapping
(value = "/BookingDetails/{id}/Hotel/getHotel",produces=MediaType.APPLICATION_JSON_VALUE)
public Hotel getHotel(@PathVariable(name="id") int hotel_id){
    System.out.println("RECEBI PEDIDO COM ID " + hotel_id);
   
return hotelbookingdetailsservice.getHotel(hotel_id);
}

@GetMapping("/{id}")
public Hotel getHotel2(@PathVariable(name="id") int hotel_id){
    System.out.println(hotel_id);
return hotelbookingdetailsservice.getHotel(hotel_id);

}
}