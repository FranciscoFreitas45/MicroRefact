package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.User;
@RestController
@CrossOrigin
public class UserStoreController {

@Autowired
 private UserStoreService userstoreservice;


@GetMapping
("/Store/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Integer id){
return userstoreservice.getUser(id);
}


@PutMapping
("/Store/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Integer id,@RequestBody User user){
userstoreservice.setUser(id,user);
}


}