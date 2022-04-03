package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceImplController {

 private UserServiceImpl userserviceimpl;


@GetMapping
("/getUserWithAuthentication")
public User getUserWithAuthentication(@RequestParam(name = "authentication") Authentication authentication){
  return userserviceimpl.getUserWithAuthentication(authentication);
}


@GetMapping
("/existsByEmail")
public boolean existsByEmail(@RequestParam(name = "email") String email){
  return userserviceimpl.existsByEmail(email);
}


@GetMapping
("/changeEmail")
public User changeEmail(@RequestParam(name = "user") User user,@RequestParam(name = "email") String email){
  return userserviceimpl.changeEmail(user,email);
}


@GetMapping
("/editUser")
public User editUser(@RequestParam(name = "user") User user,@RequestParam(name = "name") String name,@RequestParam(name = "lastName") String lastName,@RequestParam(name = "age") String age,@RequestParam(name = "phoneNumber") String phoneNumber){
  return userserviceimpl.editUser(user,name,lastName,age,phoneNumber);
}


@GetMapping
("/getUserByToken")
public User getUserByToken(@RequestParam(name = "token") String token,@RequestParam(name = "matter") String matter){
  return userserviceimpl.getUserByToken(token,matter);
}


@GetMapping
("/setNewPassword")
public User setNewPassword(@RequestParam(name = "user") User user,@RequestParam(name = "password") String password){
  return userserviceimpl.setNewPassword(user,password);
}


@GetMapping
("/getUserDetails")
public UserDetailImpl getUserDetails(@RequestParam(name = "authentication") Authentication authentication){
  return userserviceimpl.getUserDetails(authentication);
}


}