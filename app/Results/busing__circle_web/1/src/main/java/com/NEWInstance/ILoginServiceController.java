package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ILoginServiceController {

 private ILoginService iloginservice;


@GetMapping
("/login")
public User login(@RequestParam(name = "openid") String openid){
  return iloginservice.login(openid);
}


@GetMapping
("/updateUserLastLoginTimerByWeixin")
public boolean updateUserLastLoginTimerByWeixin(@RequestParam(name = "openid") String openid){
  return iloginservice.updateUserLastLoginTimerByWeixin(openid);
}


}