package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CouponCodeDaoController {

 private CouponCodeDao couponcodedao;


@GetMapping
("/lock")
public Object lock(@RequestParam(name = "Object") Object Object){
  return couponcodedao.lock(Object);
}


@GetMapping
("/merge")
public Object merge(@RequestParam(name = "Object") Object Object){
  return couponcodedao.merge(Object);
}


@GetMapping
("/build")
public List<CouponCode> build(@RequestParam(name = "coupon") Coupon coupon,@RequestParam(name = "member") Member member,@RequestParam(name = "count") Integer count){
  return couponcodedao.build(coupon,member,count);
}


}