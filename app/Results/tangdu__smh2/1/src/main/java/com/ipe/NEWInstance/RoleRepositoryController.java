package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleRepositoryController {

 private RoleRepository rolerepository;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return rolerepository.findOne(Object);
}


}