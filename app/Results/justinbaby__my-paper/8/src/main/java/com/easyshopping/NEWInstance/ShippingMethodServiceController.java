package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ShippingMethodServiceController {

 private ShippingMethodService shippingmethodservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return shippingmethodservice.findAll(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return shippingmethodservice.find(Object);
}


}