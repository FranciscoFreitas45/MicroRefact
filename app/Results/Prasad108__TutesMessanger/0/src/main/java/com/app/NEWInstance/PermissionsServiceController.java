package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PermissionsServiceController {

 private PermissionsService permissionsservice;


@GetMapping
("/find")
public Permissions find(@RequestParam(name = "id") int id){
  return permissionsservice.find(id);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "permissions") Permissions permissions){
permissionsservice.create(permissions);
}


}