package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdministrationServiceController {

 private AdministrationService administrationservice;


@GetMapping
("/getUser")
public User getUser(@RequestParam(name = "id") Integer id){
  return administrationservice.getUser(id);
}


@PutMapping
("/removeUserGroups")
public void removeUserGroups(@RequestParam(name = "id") Integer id){
administrationservice.removeUserGroups(id);
}


@PutMapping
("/add")
public void add(@RequestParam(name = "eu") EcasUser eu){
administrationservice.add(eu);
}


@PutMapping
("/deactivateEcasUsers")
public void deactivateEcasUsers(@RequestParam(name = "ids") List<Integer> ids){
administrationservice.deactivateEcasUsers(ids);
}


}