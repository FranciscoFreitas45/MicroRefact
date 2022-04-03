package com.csquard.mregister.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleRepositoryController {

 private RoleRepository rolerepository;


@GetMapping
("/findByName")
public Optional<Roles> findByName(@RequestParam(name = "roleName") RoleName roleName){
  return rolerepository.findByName(roleName);
}


@GetMapping
("/orElseThrow")
public Object orElseThrow(@RequestParam(name = "Object") Object Object){
  return rolerepository.orElseThrow(Object);
}


}