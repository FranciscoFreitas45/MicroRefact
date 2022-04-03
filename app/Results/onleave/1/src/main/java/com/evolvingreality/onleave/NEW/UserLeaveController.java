package com.evolvingreality.onleave.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.model.User;
@RestController
@CrossOrigin
public class UserLeaveController {

@Autowired
 private UserLeaveService userleaveservice;


@GetMapping
("/Leave/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long id){
return userleaveservice.getUser(id);
}


@PutMapping
("/Leave/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long id,@RequestBody User user){
userleaveservice.setUser(id,user);
}


}