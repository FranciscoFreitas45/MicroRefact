package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CouponCodeController {

 private CouponCode couponcode;

 private CouponCode couponcode;


@PutMapping
("/setIsUsed")
public void setIsUsed(@RequestParam(name = "isUsed") Boolean isUsed){
couponcode.setIsUsed(isUsed);
}


@PutMapping
("/setUsedDate")
public void setUsedDate(@RequestParam(name = "usedDate") Date usedDate){
couponcode.setUsedDate(usedDate);
}


}