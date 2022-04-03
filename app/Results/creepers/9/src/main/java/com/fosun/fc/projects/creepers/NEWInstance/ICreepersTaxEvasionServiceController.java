package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersTaxEvasionServiceController {

 private ICreepersTaxEvasionService icreeperstaxevasionservice;


@PutMapping
("/processByJob")
public void processByJob(@RequestParam(name = "JobName") String JobName){
icreeperstaxevasionservice.processByJob(JobName);
}


@GetMapping
("/findListByName")
public List<TCreepersTaxEvasion> findListByName(@RequestParam(name = "name") String name){
  return icreeperstaxevasionservice.findListByName(name);
}


}