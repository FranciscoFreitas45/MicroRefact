package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleServiceController {

 private RoleService roleservice;


@GetMapping
("/queryByName")
public Role queryByName(@RequestParam(name = "roleName") String roleName){
  return roleservice.queryByName(roleName);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return roleservice.insert(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return roleservice.update(Object);
}


@GetMapping
("/updateStatus")
public int updateStatus(@RequestParam(name = "role") Role role){
  return roleservice.updateStatus(role);
}


@GetMapping
("/queryAll")
public List<Role> queryAll(@RequestParam(name = "rolestatus") String rolestatus){
  return roleservice.queryAll(rolestatus);
}


@GetMapping
("/count")
public int count(@RequestParam(name = "roleStatus") String roleStatus){
  return roleservice.count(roleStatus);
}


@GetMapping
("/queryByPager")
public List<Role> queryByPager(@RequestParam(name = "roleStatus") String roleStatus,@RequestParam(name = "pager") Pager pager){
  return roleservice.queryByPager(roleStatus,pager);
}


}