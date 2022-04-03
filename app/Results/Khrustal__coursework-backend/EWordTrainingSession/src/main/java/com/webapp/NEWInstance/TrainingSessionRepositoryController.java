package com.webapp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrainingSessionRepositoryController {

 private TrainingSessionRepository trainingsessionrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return trainingsessionrepository.findById(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return trainingsessionrepository.findAll(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return trainingsessionrepository.save(Object);
}


}