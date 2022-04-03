package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleServiceController {

 private RoleService roleservice;


@GetMapping
("/findByName")
public Role findByName(@RequestParam(name = "name") String name){
  return roleservice.findByName(name);
}


}