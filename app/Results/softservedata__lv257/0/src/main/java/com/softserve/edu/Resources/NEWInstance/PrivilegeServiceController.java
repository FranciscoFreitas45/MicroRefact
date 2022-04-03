package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PrivilegeServiceController {

 private PrivilegeService privilegeservice;


@GetMapping
("/getAllPrivileges")
public List<Privilege> getAllPrivileges(){
  return privilegeservice.getAllPrivileges();
}


}