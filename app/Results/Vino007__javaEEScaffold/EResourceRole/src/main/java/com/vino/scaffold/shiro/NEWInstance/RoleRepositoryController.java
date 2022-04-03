package com.vino.scaffold.shiro.NEWInstance;
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


@GetMapping
("/findByName")
public Role findByName(@RequestParam(name = "name") String name){
  return rolerepository.findByName(name);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return rolerepository.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return rolerepository.findAll(Object);
}


@PutMapping
("/deleteAssociateById")
public void deleteAssociateById(@RequestParam(name = "roleIds") Long roleIds){
rolerepository.deleteAssociateById(roleIds);
}


}