package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersSactionServiceController {

 private ICreepersSactionService icreeperssactionservice;


@PutMapping
("/processByJob")
public void processByJob(@RequestParam(name = "JobName") String JobName){
icreeperssactionservice.processByJob(JobName);
}


}