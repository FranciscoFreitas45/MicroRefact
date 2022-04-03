package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysUserServiceController {

 private SysUserService sysuserservice;


@GetMapping
("/findByPage")
public Object findByPage(@RequestParam(name = "Object") Object Object){
  return sysuserservice.findByPage(Object);
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return sysuserservice.get(Object);
}


@GetMapping
("/findByName")
public SysUser findByName(@RequestParam(name = "name") String name){
  return sysuserservice.findByName(name);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return sysuserservice.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return sysuserservice.findAll(Object);
}


@GetMapping
("/findByProperty")
public Object findByProperty(@RequestParam(name = "Object") Object Object){
  return sysuserservice.findByProperty(Object);
}


}