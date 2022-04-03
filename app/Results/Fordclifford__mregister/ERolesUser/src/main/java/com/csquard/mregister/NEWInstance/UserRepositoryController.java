package com.csquard.mregister.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findByUsernameOrEmail")
public Optional<User> findByUsernameOrEmail(@RequestParam(name = "username") String username,@RequestParam(name = "email") String email){
  return userrepository.findByUsernameOrEmail(username,email);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return userrepository.findById(Object);
}


@GetMapping
("/existsByUsername")
public Boolean existsByUsername(@RequestParam(name = "username") String username){
  return userrepository.existsByUsername(username);
}


@GetMapping
("/existsByEmail")
public Boolean existsByEmail(@RequestParam(name = "email") String email){
  return userrepository.existsByEmail(email);
}


@GetMapping
("/findByUsername")
public Optional<User> findByUsername(@RequestParam(name = "username") String username){
  return userrepository.findByUsername(username);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return userrepository.save(Object);
}


}