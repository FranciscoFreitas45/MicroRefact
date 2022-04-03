package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRoleServiceController {

 private UserRoleService userroleservice;


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return userroleservice.insert(Object);
}


}