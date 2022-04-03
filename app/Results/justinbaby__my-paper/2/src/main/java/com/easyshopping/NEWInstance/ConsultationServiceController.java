package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConsultationServiceController {

 private ConsultationService consultationservice;


@GetMapping
("/findList")
public List<Consultation> findList(@RequestParam(name = "member") Member member,@RequestParam(name = "product") Product product,@RequestParam(name = "isShow") Boolean isShow,@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<Order> orders,@RequestParam(name = "cacheRegion") String cacheRegion){
  return consultationservice.findList(member,product,isShow,count,filters,orders,cacheRegion);
}


}