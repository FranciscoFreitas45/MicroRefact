package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/findByEmail")
public User findByEmail(@RequestParam(name = "email") String email){
  return userservice.findByEmail(email);
}


@GetMapping
("/updateCommnetNum")
public int updateCommnetNum(@RequestParam(name = "email") String email){
  return userservice.updateCommnetNum(email);
}


}