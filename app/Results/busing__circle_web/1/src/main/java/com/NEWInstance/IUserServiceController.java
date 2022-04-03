package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IUserServiceController {

 private IUserService iuserservice;


@GetMapping
("/addWeixinUser")
public boolean addWeixinUser(@RequestParam(name = "user") User user){
  return iuserservice.addWeixinUser(user);
}


@GetMapping
("/getUserByInviteCode")
public User getUserByInviteCode(@RequestParam(name = "inviteCode") String inviteCode){
  return iuserservice.getUserByInviteCode(inviteCode);
}


}