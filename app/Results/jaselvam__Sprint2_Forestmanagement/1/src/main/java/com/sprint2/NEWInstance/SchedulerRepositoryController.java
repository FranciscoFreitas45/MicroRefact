package com.sprint2.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SchedulerRepositoryController {

 private SchedulerRepository schedulerrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return schedulerrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return schedulerrepository.save(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return schedulerrepository.deleteById(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return schedulerrepository.findAll(Object);
}


}