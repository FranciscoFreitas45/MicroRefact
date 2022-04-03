package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReviewController {

 private Review review;

 private Review review;


@PutMapping
("/setIp")
public void setIp(@RequestParam(name = "ip") String ip){
review.setIp(ip);
}


@PutMapping
("/setProduct")
public void setProduct(@RequestParam(name = "product") Product product){
review.setProduct(product);
}


@PutMapping
("/setIsShow")
public void setIsShow(@RequestParam(name = "isShow") Boolean isShow){
review.setIsShow(isShow);
}


}