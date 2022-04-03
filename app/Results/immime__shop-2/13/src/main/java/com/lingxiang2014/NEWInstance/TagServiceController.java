package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagServiceController {

 private TagService tagservice;


@GetMapping
("/findList")
public List<Tag> findList(@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<Order> orders,@RequestParam(name = "cacheRegion") String cacheRegion){
  return tagservice.findList(count,filters,orders,cacheRegion);
}


}