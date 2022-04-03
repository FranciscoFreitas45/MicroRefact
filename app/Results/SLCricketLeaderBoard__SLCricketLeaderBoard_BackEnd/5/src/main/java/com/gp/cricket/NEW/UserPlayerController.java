package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.User;
@RestController
@CrossOrigin
public class UserPlayerController {

@Autowired
 private UserPlayerService userplayerservice;


@GetMapping
("/Player/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Integer userIdv2){
return userplayerservice.getUserId(userIdv2);
}


@PutMapping
("/Player/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Integer userIdv2,@RequestBody User userId){
userplayerservice.setUserId(userIdv2,userId);
}


}