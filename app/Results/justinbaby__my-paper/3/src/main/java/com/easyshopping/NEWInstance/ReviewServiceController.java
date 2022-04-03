package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReviewServiceController {

 private ReviewService reviewservice;


@GetMapping
("/findPage")
public Page<Review> findPage(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product,@RequestParam(name = "type") Type type,@RequestParam(name = "isShow") Boolean isShow,@RequestParam(name = "pageable") Pageable pageable){
  return reviewservice.findPage(member,product,type,isShow,pageable);
}


@GetMapping
("/isReviewed")
public boolean isReviewed(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product){
  return reviewservice.isReviewed(member,product);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return reviewservice.save(Object);
}


@GetMapping
("/count")
public Long count(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product,@RequestParam(name = "type") Type type,@RequestParam(name = "isShow") Boolean isShow){
  return reviewservice.count(member,product,type,isShow);
}


@GetMapping
("/findList")
public List<Review> findList(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product,@RequestParam(name = "type") Type type,@RequestParam(name = "isShow") Boolean isShow,@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<Order> orders,@RequestParam(name = "cacheRegion") String cacheRegion){
  return reviewservice.findList(member,product,type,isShow,count,filters,orders,cacheRegion);
}


}