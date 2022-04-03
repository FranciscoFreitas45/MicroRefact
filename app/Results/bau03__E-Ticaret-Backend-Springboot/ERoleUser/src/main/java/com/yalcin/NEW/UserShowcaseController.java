package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.User;
@RestController
@CrossOrigin
public class UserShowcaseController {

@Autowired
 private UserShowcaseService usershowcaseservice;


@GetMapping
("/Showcase/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Integer id){
return usershowcaseservice.getUser(id);
}


@PutMapping
("/Showcase/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Integer id,@RequestBody User user){
usershowcaseservice.setUser(id,user);
}


}