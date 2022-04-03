package com.zammc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderServiceController {

 private OrderService orderservice;


@GetMapping
("/queryOrderCount")
public Long queryOrderCount(){
  return orderservice.queryOrderCount();
}


@GetMapping
("/queryTotalPrice")
public Double queryTotalPrice(){
  return orderservice.queryTotalPrice();
}


}