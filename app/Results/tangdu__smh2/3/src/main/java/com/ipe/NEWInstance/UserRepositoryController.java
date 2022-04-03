package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return userrepository.findOne(Object);
}


}