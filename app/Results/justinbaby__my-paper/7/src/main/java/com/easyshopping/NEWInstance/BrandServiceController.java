package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BrandServiceController {

 private BrandService brandservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return brandservice.findAll(Object);
}


@GetMapping
("/findList")
public List<Brand> findList(@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<Order> orders,@RequestParam(name = "cacheRegion") String cacheRegion){
  return brandservice.findList(count,filters,orders,cacheRegion);
}


}