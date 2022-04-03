package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PromotionServiceController {

 private PromotionService promotionservice;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return promotionservice.find(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return promotionservice.findAll(Object);
}


@GetMapping
("/findList")
public List<Promotion> findList(@RequestParam(name = "hasBegun") Boolean hasBegun,@RequestParam(name = "hasEnded") Boolean hasEnded,@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<Order> orders,@RequestParam(name = "cacheRegion") String cacheRegion){
  return promotionservice.findList(hasBegun,hasEnded,count,filters,orders,cacheRegion);
}


}