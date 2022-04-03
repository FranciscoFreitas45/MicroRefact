package com.cg.oms.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.model.User;
@RestController
@CrossOrigin
public class UserOrderController {

@Autowired
 private UserOrderService userorderservice;


@GetMapping
("/Order/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userorderservice.getUser(userId);
}


@PutMapping
("/Order/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userorderservice.setUser(userId,user);
}


}