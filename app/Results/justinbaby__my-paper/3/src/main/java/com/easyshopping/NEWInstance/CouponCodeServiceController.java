package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CouponCodeServiceController {

 private CouponCodeService couponcodeservice;


@GetMapping
("/findByCode")
public CouponCode findByCode(@RequestParam(name = "code") String code){
  return couponcodeservice.findByCode(code);
}


@GetMapping
("/count")
public Long count(@RequestParam(name = "coupon") Coupon coupon,@RequestParam(name = "member") Member member,@RequestParam(name = "hasBegun") Boolean hasBegun,@RequestParam(name = "hasExpired") Boolean hasExpired,@RequestParam(name = "isUsed") Boolean isUsed){
  return couponcodeservice.count(coupon,member,hasBegun,hasExpired,isUsed);
}


}