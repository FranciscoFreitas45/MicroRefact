package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.User;
@RestController
@CrossOrigin
public class UserProductController {

@Autowired
 private UserProductService userproductservice;


@GetMapping
("/Product/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Integer id){
return userproductservice.getUser(id);
}


@PutMapping
("/Product/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Integer id,@RequestBody User user){
userproductservice.setUser(id,user);
}


}