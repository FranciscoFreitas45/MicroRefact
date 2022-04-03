package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getUserNameByUserId")
public String getUserNameByUserId(@RequestParam(name = "userId") String userId){
  return userservice.getUserNameByUserId(userId);
}


@GetMapping
("/findByUserId")
public PtuUser findByUserId(@RequestParam(name = "userId") String userId){
  return userservice.findByUserId(userId);
}


@GetMapping
("/findOne")
public PtuUser findOne(@RequestParam(name = "request") HttpServletRequest request){
  return userservice.findOne(request);
}


}