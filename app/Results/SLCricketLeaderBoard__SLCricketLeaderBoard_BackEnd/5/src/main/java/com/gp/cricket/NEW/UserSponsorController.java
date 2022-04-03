package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.User;
@RestController
@CrossOrigin
public class UserSponsorController {

@Autowired
 private UserSponsorService usersponsorservice;


@GetMapping
("/Sponsor/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Integer userIdv2){
return usersponsorservice.getUserId(userIdv2);
}


@PutMapping
("/Sponsor/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Integer userIdv2,@RequestBody User userId){
usersponsorservice.setUserId(userIdv2,userId);
}


}