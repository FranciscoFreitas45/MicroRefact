package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CouponController {

 private Coupon coupon;

 private Coupon coupon;


@GetMapping
("/hasBegun")
public boolean hasBegun(){
  return coupon.hasBegun();
}


@GetMapping
("/hasExpired")
public boolean hasExpired(){
  return coupon.hasExpired();
}


}