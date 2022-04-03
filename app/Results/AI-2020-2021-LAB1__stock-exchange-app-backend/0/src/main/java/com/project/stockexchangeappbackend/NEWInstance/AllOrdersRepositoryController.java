package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AllOrdersRepositoryController {

 private AllOrdersRepository allordersrepository;


@GetMapping
("/countByUser")
public Long countByUser(@RequestParam(name = "user") User user){
  return allordersrepository.countByUser(user);
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return allordersrepository.equals(Object);
}


}