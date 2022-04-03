package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/queryByCompanyId")
public List<User> queryByCompanyId(@RequestParam(name = "companyId") String companyId){
  return userservice.queryByCompanyId(companyId);
}


@GetMapping
("/queryByRoleName")
public List<User> queryByRoleName(@RequestParam(name = "roleName") String roleName){
  return userservice.queryByRoleName(roleName);
}


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return userservice.queryAll(Object);
}


@GetMapping
("/queryEmail")
public List<User> queryEmail(@RequestParam(name = "ids") String ids){
  return userservice.queryEmail(ids);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return userservice.insert(Object);
}


}