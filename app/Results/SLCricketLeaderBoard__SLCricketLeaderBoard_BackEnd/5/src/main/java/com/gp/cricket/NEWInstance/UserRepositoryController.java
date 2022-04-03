package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return userrepository.existsById(Object);
}


@GetMapping
("/findByUserId")
public User findByUserId(@RequestParam(name = "userId") Integer userId){
  return userrepository.findByUserId(userId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


}