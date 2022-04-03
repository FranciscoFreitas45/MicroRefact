package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleRepositoryController {

 private RoleRepository rolerepository;


@GetMapping
("/findByRole")
public Optional<Role> findByRole(@RequestParam(name = "role") Roles role){
  return rolerepository.findByRole(role);
}


@GetMapping
("/orElseThrow")
public Object orElseThrow(@RequestParam(name = "Object") Object Object){
  return rolerepository.orElseThrow(Object);
}


}