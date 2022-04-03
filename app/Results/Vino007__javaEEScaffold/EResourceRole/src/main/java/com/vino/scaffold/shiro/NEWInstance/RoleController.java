package com.vino.scaffold.shiro.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleController {

 private Role role;

 private Role role;


@PutMapping
("/setDescription")
public void setDescription(@RequestParam(name = "description") String description){
role.setDescription(description);
}


@PutMapping
("/setAvailable")
public void setAvailable(@RequestParam(name = "available") Boolean available){
role.setAvailable(available);
}


@PutMapping
("/setResources")
public void setResources(@RequestParam(name = "resources") Set<Resource> resources){
role.setResources(resources);
}


}