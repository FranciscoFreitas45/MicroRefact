package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderRepositoryController {

 private OrderRepository orderrepository;


@GetMapping
("/findAllByUsername")
public List<Order> findAllByUsername(@RequestParam(name = "userId") Integer userId){
  return orderrepository.findAllByUsername(userId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return orderrepository.save(Object);
}


}