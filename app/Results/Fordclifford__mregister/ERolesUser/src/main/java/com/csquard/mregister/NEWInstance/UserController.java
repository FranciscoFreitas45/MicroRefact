package com.csquard.mregister.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private UserRepository userrepository;


@PutMapping
("/setPassword/{id}")
public void setPassword(@PathVariable(name = "id") Long id,@RequestParam(name = "password") String password){
 userrepository.setPassword(id,password);
}


@PutMapping
("/setRoles/{id}")
public void setRoles(@PathVariable(name = "id") Long id,@RequestParam(name = "roles") Set<Roles> roles){
 userrepository.setRoles(id,roles);
}


}