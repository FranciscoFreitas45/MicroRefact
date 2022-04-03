package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/findByUsername")
public List<User> findByUsername(@RequestParam(name = "username") String username){
  return userservice.findByUsername(username);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userservice.save(Object);
}


}