package com.webapp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return userrepository.findById(Object);
}


@GetMapping
("/findByUsername")
public Optional<User> findByUsername(@RequestParam(name = "username") String username){
  return userrepository.findByUsername(username);
}


}