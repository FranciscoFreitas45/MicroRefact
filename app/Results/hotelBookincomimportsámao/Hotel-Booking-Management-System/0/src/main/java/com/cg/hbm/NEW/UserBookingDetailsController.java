package com.cg.hbm.NEW;
import org.springframework.web.bind.annotation.*;
import com.cg.hbm.entites.User;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
public class UserBookingDetailsController {
@Autowired
 private UserBookingDetailsService userbookingdetailsservice;


@GetMapping
("/BookingDetails/{id}/User/getUser")
public User getUser(@PathVariable(name="id") int user_id){
    System.out.println("RECEBI PEDIDO COM ID " + user_id);
return userbookingdetailsservice.getUser(user_id);
}


@PutMapping
("/BookingDetails/{id}/User/setUser")
public void setUser(@PathVariable(name="id") int user_id,@RequestBody User user){
     System.out.println("RECEBI PEDIDO COM ID " + user_id);
     System.out.println("E BODY " + user);
userbookingdetailsservice.setUser(user_id,user);
}


}