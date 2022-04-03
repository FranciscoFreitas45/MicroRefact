package com.sda.inTeams.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TaskRepositoryController {

 private TaskRepository taskrepository;


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return taskrepository.count(Object);
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return taskrepository.saveAll(Object);
}


@GetMapping
("/findAllByProject")
public List<Task> findAllByProject(@RequestParam(name = "project") Project project){
  return taskrepository.findAllByProject(project);
}


@GetMapping
("/findAllByUserResponsible")
public List<Task> findAllByUserResponsible(@RequestParam(name = "user") User user){
  return taskrepository.findAllByUserResponsible(user);
}


}