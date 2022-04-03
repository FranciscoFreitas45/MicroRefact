package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersCourtDisInfoServiceController {

 private ICreepersCourtDisInfoService icreeperscourtdisinfoservice;


@GetMapping
("/findListByName")
public List<TCreepersCourtDisInfo> findListByName(@RequestParam(name = "name") String name){
  return icreeperscourtdisinfoservice.findListByName(name);
}


}