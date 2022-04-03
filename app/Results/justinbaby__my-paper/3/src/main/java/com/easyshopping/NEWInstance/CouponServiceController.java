package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CouponServiceController {

 private CouponService couponservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return couponservice.findAll(Object);
}


@GetMapping
("/findList")
public Object findList(@RequestParam(name = "Object") Object Object){
  return couponservice.findList(Object);
}


}