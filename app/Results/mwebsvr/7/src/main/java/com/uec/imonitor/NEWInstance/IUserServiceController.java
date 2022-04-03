package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IUserServiceController {

 private IUserService iuserservice;


@GetMapping
("/findByUserName")
public UserEntity findByUserName(@RequestParam(name = "userName") String userName){
  return iuserservice.findByUserName(userName);
}


}