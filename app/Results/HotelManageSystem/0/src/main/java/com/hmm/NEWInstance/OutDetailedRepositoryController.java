package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OutDetailedRepositoryController {

 private OutDetailedRepository outdetailedrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.save(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.count(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.findAll(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.deleteById(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.findById(Object);
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.existsById(Object);
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.findAllById(Object);
}


@GetMapping
("/deleteAll")
public Object deleteAll(@RequestParam(name = "Object") Object Object){
  return outdetailedrepository.deleteAll(Object);
}


}