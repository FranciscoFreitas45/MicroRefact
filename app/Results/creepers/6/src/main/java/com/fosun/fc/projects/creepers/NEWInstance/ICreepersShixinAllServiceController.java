package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersShixinAllServiceController {

 private ICreepersShixinAllService icreepersshixinallservice;


@PutMapping
("/processByJob")
public void processByJob(@RequestParam(name = "JobName") String JobName){
icreepersshixinallservice.processByJob(JobName);
}


@GetMapping
("/findListByName")
public List<TCreepersShixinAll> findListByName(@RequestParam(name = "name") String name){
  return icreepersshixinallservice.findListByName(name);
}


}