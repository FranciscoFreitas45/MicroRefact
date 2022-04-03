package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleDaoController {

 private RoleDao roledao;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return roledao.findAll(Object);
}


@GetMapping
("/findUniqueByProperty")
public Object findUniqueByProperty(@RequestParam(name = "Object") Object Object){
  return roledao.findUniqueByProperty(Object);
}


}