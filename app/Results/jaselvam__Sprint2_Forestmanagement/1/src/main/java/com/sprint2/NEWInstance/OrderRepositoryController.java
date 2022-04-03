package com.sprint2.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderRepositoryController {

 private OrderRepository orderrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return orderrepository.findById(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return orderrepository.save(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return orderrepository.deleteById(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return orderrepository.findAll(Object);
}


@GetMapping
("/forEach")
public Object forEach(@RequestParam(name = "Object") Object Object){
  return orderrepository.forEach(Object);
}


}