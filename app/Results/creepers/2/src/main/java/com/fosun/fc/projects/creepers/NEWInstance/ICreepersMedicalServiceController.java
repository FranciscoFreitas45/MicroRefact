package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersMedicalServiceController {

 private ICreepersMedicalService icreepersmedicalservice;


@PutMapping
("/processByJob")
public void processByJob(@RequestParam(name = "jobName") String jobName){
icreepersmedicalservice.processByJob(jobName);
}


@GetMapping
("/findListByKey")
public List<TCreepersMedical> findListByKey(@RequestParam(name = "key") String key){
  return icreepersmedicalservice.findListByKey(key);
}


}