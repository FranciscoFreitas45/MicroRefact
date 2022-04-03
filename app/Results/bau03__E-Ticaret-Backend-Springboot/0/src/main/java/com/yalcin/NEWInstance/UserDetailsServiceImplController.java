package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDetailsServiceImplController {

 private UserDetailsServiceImpl userdetailsserviceimpl;


@GetMapping
("/loadUserByEmail")
public UserDetails loadUserByEmail(@RequestParam(name = "email") String email){
  return userdetailsserviceimpl.loadUserByEmail(email);
}


}