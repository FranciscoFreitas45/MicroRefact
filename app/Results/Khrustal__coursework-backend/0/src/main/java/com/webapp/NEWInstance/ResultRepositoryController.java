package com.webapp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResultRepositoryController {

 private ResultRepository resultrepository;


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return resultrepository.existsById(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return resultrepository.delete(Object);
}


}