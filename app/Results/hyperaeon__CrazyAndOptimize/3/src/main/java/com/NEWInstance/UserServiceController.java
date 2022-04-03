package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getByLoginName")
public User getByLoginName(@RequestParam(name = "loginName") String loginName){
  return userservice.getByLoginName(loginName);
}


}