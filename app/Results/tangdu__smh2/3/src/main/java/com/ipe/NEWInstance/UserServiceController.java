package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return userservice.get(Object);
}


@PutMapping
("/logout")
public void logout(){
userservice.logout();
}


}