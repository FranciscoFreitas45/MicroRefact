package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.User;
@RestController
@CrossOrigin
public class UserTotalOrderController {

@Autowired
 private UserTotalOrderService usertotalorderservice;


@GetMapping
("/TotalOrder/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Integer id){
return usertotalorderservice.getUser(id);
}


@PutMapping
("/TotalOrder/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Integer id,@RequestBody User user){
usertotalorderservice.setUser(id,user);
}


}