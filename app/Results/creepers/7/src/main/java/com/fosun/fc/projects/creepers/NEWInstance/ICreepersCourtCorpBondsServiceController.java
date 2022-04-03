package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersCourtCorpBondsServiceController {

 private ICreepersCourtCorpBondsService icreeperscourtcorpbondsservice;


@PutMapping
("/processByJob")
public void processByJob(@RequestParam(name = "jobName") String jobName){
icreeperscourtcorpbondsservice.processByJob(jobName);
}


@GetMapping
("/findListByName")
public List<TCreepersCourtCorpBonds> findListByName(@RequestParam(name = "name") String name){
  return icreeperscourtcorpbondsservice.findListByName(name);
}


}