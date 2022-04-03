package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "obj") Object obj){
  return user.equals(obj);
}


@GetMapping
("/hashCode")
public int hashCode(){
  return user.hashCode();
}


@GetMapping
("/setRoles/{id}")
public User setRoles(@PathVariable(name = "id") Long id,@RequestParam(name = "roles") Collection<Role> roles){
 return userdaoimpl.setRoles(id,roles);
}


@GetMapping
("/setPassword/{id}")
public User setPassword(@PathVariable(name = "id") Long id,@RequestParam(name = "password") String password){
 return userdaoimpl.setPassword(id,password);
}


@GetMapping
("/setUsername/{id}")
public User setUsername(@PathVariable(name = "id") Long id,@RequestParam(name = "username") String username){
 return userdaoimpl.setUsername(id,username);
}


@GetMapping
("/setUserDetails/{id}")
public User setUserDetails(@PathVariable(name = "id") Long id,@RequestParam(name = "userDetails") UserDetails userDetails){
 return userdaoimpl.setUserDetails(id,userDetails);
}


@GetMapping
("/isEnabled/{id}")
public boolean isEnabled(@PathVariable(name = "id") Long id){
 return userdaoimpl.isEnabled(id);
}


@GetMapping
("/setEnabled/{id}")
public User setEnabled(@PathVariable(name = "id") Long id,@RequestParam(name = "enabled") boolean enabled){
 return userdaoimpl.setEnabled(id,enabled);
}


}