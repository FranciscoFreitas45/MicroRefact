package com.cg.oms.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.model.User;
@RestController
@CrossOrigin
public class UserCartController {

@Autowired
 private UserCartService usercartservice;


@GetMapping
("/Cart/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return usercartservice.getUser(userId);
}


@PutMapping
("/Cart/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
usercartservice.setUser(userId,user);
}


}