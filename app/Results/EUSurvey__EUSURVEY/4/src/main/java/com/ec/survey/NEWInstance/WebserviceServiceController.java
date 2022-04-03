package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WebserviceServiceController {

 private WebserviceService webserviceservice;


@GetMapping
("/getServiceRequest")
public ServiceRequest getServiceRequest(@RequestParam(name = "userId") Integer userId){
  return webserviceservice.getServiceRequest(userId);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "task") WebserviceTask task){
webserviceservice.save(task);
}


@GetMapping
("/startTask")
public boolean startTask(@RequestParam(name = "task") WebserviceTask task,@RequestParam(name = "locale") Locale locale){
  return webserviceservice.startTask(task,locale);
}


@PutMapping
("/increaseServiceRequest")
public void increaseServiceRequest(@RequestParam(name = "userId") Integer userId){
webserviceservice.increaseServiceRequest(userId);
}


@GetMapping
("/getWaitingTokens")
public int getWaitingTokens(@RequestParam(name = "group") ParticipationGroup group){
  return webserviceservice.getWaitingTokens(group);
}


@GetMapping
("/get")
public WebserviceTask get(@RequestParam(name = "id") int id){
  return webserviceservice.get(id);
}


}