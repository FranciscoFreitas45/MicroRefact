package com.vino.scaffold.shiro.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleServiceController {

 private RoleService roleservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return roleservice.findAll(Object);
}


@PutMapping
("/connectRoleAndResource")
public void connectRoleAndResource(@RequestParam(name = "roleId") Long roleId,@RequestParam(name = "resourceIds") Long resourceIds){
roleservice.connectRoleAndResource(roleId,resourceIds);
}


}