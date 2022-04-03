package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaskServiceController {

 private TaskService taskservice;


@GetMapping
("/getAllTasksByUserResponsibleFor")
public List<Task> getAllTasksByUserResponsibleFor(@RequestParam(name = "user") User user){
  return taskservice.getAllTasksByUserResponsibleFor(user);
}


@GetMapping
("/getAll")
public List<Task> getAll(){
  return taskservice.getAll();
}


}