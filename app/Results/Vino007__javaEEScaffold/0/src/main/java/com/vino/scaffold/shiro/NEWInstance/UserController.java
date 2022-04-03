package com.vino.scaffold.shiro.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;

 private User user;


@PutMapping
("/setLastLoginTime")
public void setLastLoginTime(@RequestParam(name = "lastLoginTime") Date lastLoginTime){
user.setLastLoginTime(lastLoginTime);
}


@PutMapping
("/setLoginTime")
public void setLoginTime(@RequestParam(name = "loginTime") Date loginTime){
user.setLoginTime(loginTime);
}


}