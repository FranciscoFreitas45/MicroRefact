package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersCourtDishonestyServiceController {

 private ICreepersCourtDishonestyService icreeperscourtdishonestyservice;


@PutMapping
("/processByJob")
public void processByJob(@RequestParam(name = "JobName") String JobName){
icreeperscourtdishonestyservice.processByJob(JobName);
}


@GetMapping
("/findListByName")
public List<TCreepersCourtDishonest> findListByName(@RequestParam(name = "name") String name){
  return icreeperscourtdishonestyservice.findListByName(name);
}


}