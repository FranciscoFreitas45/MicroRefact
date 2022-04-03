package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.User;
@RestController
@CrossOrigin
public class UserAdressController {

@Autowired
 private UserAdressService useradressservice;


@GetMapping
("/Adress/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Integer id){
return useradressservice.getUser(id);
}


@PutMapping
("/Adress/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Integer id,@RequestBody User user){
useradressservice.setUser(id,user);
}


}