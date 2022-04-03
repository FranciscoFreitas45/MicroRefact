package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import com.sda.inTeams.DTO.*;
 import java.util.*;
 import com.sda.inTeams.model.Task.*;
 import com.sda.inTeams.service.*;
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