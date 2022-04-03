package com.vino.scaffold.shiro.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/findByUsername")
public User findByUsername(@RequestParam(name = "username") String username){
  return userservice.findByUsername(username);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "user") User user){
userservice.update(user);
}


@PutMapping
("/saveWithCheckDuplicate")
public void saveWithCheckDuplicate(@RequestParam(name = "users") List<User> users){
userservice.saveWithCheckDuplicate(users);
}


}