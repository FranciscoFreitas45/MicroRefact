package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/createVerificationTokenForUser")
public VerificationToken createVerificationTokenForUser(@RequestParam(name = "user") User user,@RequestParam(name = "token") String token){
  return userservice.createVerificationTokenForUser(user,token);
}


@GetMapping
("/getUser")
public User getUser(@RequestParam(name = "username") String username){
  return userservice.getUser(username);
}


}