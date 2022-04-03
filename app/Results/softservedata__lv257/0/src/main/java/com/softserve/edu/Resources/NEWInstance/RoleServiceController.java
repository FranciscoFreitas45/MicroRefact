package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleServiceController {

 private RoleService roleservice;


@GetMapping
("/getRolePrivileges")
public List<String> getRolePrivileges(@RequestParam(name = "roleName") String roleName){
  return roleservice.getRolePrivileges(roleName);
}


@GetMapping
("/getAllRoles")
public List<String> getAllRoles(){
  return roleservice.getAllRoles();
}


}