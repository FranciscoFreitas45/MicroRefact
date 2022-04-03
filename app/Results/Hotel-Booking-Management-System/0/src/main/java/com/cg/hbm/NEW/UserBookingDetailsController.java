package com.cg.hbm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.entites.User;
@RestController
@CrossOrigin
public class UserBookingDetailsController {

@Autowired
 private UserBookingDetailsService userbookingdetailsservice;


@GetMapping
("/BookingDetails/{id}/User/getUser")
public User getUser(@PathVariable(name="id") int user_id){
return userbookingdetailsservice.getUser(user_id);
}


@PutMapping
("/BookingDetails/{id}/User/setUser")
public void setUser(@PathVariable(name="id") int user_id,@RequestBody User user){
userbookingdetailsservice.setUser(user_id,user);
}


}