package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ActiveSessionsRepositoryController {

 private ActiveSessionsRepository activesessionsrepository;


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return activesessionsrepository.existsById(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return activesessionsrepository.delete(Object);
}


}