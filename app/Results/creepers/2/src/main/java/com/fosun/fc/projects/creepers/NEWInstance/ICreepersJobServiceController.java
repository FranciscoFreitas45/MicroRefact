package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersJobServiceController {

 private ICreepersJobService icreepersjobservice;


@GetMapping
("/findJob")
public CreepersJobDTO findJob(@RequestParam(name = "jobName") String jobName){
  return icreepersjobservice.findJob(jobName);
}


@PutMapping
("/updateResumeRequestByJobName")
public void updateResumeRequestByJobName(@RequestParam(name = "jobName") String jobName,@RequestParam(name = "request") String request){
icreepersjobservice.updateResumeRequestByJobName(jobName,request);
}


}