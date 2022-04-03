package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/getByLoginName")
public User getByLoginName(@RequestParam(name = "loginName") String loginName){
  return userrepository.getByLoginName(loginName);
}


@GetMapping
("/save")
public User save(@RequestParam(name = "user") User user){
  return userrepository.save(user);
}


}