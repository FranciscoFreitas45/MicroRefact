package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.User;
@RestController
@CrossOrigin
public class UserPurseController {

@Autowired
 private UserPurseService userpurseservice;


@GetMapping
("/Purse/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Integer id){
return userpurseservice.getUser(id);
}


@PutMapping
("/Purse/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Integer id,@RequestBody User user){
userpurseservice.setUser(id,user);
}


}