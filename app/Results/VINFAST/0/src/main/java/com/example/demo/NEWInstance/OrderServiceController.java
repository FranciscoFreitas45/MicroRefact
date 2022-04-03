package com.example.demo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderServiceController {

 private OrderService orderservice;


@GetMapping
("/save")
public Order save(@RequestParam(name = "entity") Order entity){
  return orderservice.save(entity);
}


@GetMapping
("/findByUsername")
public List<Order> findByUsername(@RequestParam(name = "username") String username){
  return orderservice.findByUsername(username);
}


@GetMapping
("/getOne")
public Order getOne(@RequestParam(name = "id") Long id){
  return orderservice.getOne(id);
}


}