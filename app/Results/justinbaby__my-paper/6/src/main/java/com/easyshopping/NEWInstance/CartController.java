package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CartController {

 private Cart cart;

 private Cart cart;


@GetMapping
("/isEmpty")
public boolean isEmpty(){
  return cart.isEmpty();
}


@GetMapping
("/isCouponAllowed")
public boolean isCouponAllowed(){
  return cart.isCouponAllowed();
}


@GetMapping
("/isValid")
public boolean isValid(@RequestParam(name = "coupon") Coupon coupon){
  return cart.isValid(coupon);
}


}