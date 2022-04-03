package org.live.NEWInstance;
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
("/findRolesByUsername")
public List<Role> findRolesByUsername(@RequestParam(name = "username") String username){
  return rolerepository.findRolesByUsername(username);
}


}