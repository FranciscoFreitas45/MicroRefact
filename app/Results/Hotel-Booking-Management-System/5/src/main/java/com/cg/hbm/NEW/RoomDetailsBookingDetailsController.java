package com.cg.hbm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.entites.RoomDetails;
@RestController
@CrossOrigin
public class RoomDetailsBookingDetailsController {

@Autowired
 private RoomDetailsBookingDetailsService roomdetailsbookingdetailsservice;


@PutMapping
("/BookingDetails/{id}/RoomDetails/setRoomdetails")
public void setRoomdetails(@PathVariable(name="id") int room_id,@RequestBody RoomDetails roomdetails){
roomdetailsbookingdetailsservice.setRoomdetails(room_id,roomdetails);
}


@GetMapping
("/BookingDetails/{id}/RoomDetails/getRoomdetails")
public RoomDetails getRoomdetails(@PathVariable(name="id") int room_id){
return roomdetailsbookingdetailsservice.getRoomdetails(room_id);
}


}