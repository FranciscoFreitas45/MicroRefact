package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/signupUser")
public User signupUser(@RequestParam(name = "user") User user){
  return userservice.signupUser(user);
}


@GetMapping
("/updateUser")
public Integer updateUser(@RequestParam(name = "updateUser") User updateUser){
  return userservice.updateUser(updateUser);
}


@GetMapping
("/userAccountDeactivate")
public Integer userAccountDeactivate(@RequestParam(name = "userId") Integer userId){
  return userservice.userAccountDeactivate(userId);
}


@GetMapping
("/registerUser")
public User registerUser(@RequestParam(name = "user") User user){
  return userservice.registerUser(user);
}


}