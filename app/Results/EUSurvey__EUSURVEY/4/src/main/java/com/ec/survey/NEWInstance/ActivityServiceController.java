package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActivityServiceController {

 private ActivityService activityservice;


@GetMapping
("/isLogEnabled")
public boolean isLogEnabled(@RequestParam(name = "activityCode") int activityCode){
  return activityservice.isLogEnabled(activityCode);
}


@PutMapping
("/log")
public void log(@RequestParam(name = "activityCode") int activityCode,@RequestParam(name = "oldValue") String oldValue,@RequestParam(name = "newValue") String newValue,@RequestParam(name = "userId") int userId,@RequestParam(name = "surveyUID") String surveyUID){
activityservice.log(activityCode,oldValue,newValue,userId,surveyUID);
}


}