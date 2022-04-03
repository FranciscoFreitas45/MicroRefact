package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.User;
@RestController
@CrossOrigin
public class UserSellerBeginController {

@Autowired
 private UserSellerBeginService usersellerbeginservice;


@GetMapping
("/SellerBegin/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Integer id){
return usersellerbeginservice.getUser(id);
}


@PutMapping
("/SellerBegin/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Integer id,@RequestBody User user){
usersellerbeginservice.setUser(id,user);
}


}