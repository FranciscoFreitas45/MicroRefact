package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderServiceController {

 private OrderService orderservice;


@GetMapping
("/waitingPaymentCount")
public Long waitingPaymentCount(@RequestParam(name = "member") Member member){
  return orderservice.waitingPaymentCount(member);
}


@GetMapping
("/waitingShippingCount")
public Long waitingShippingCount(@RequestParam(name = "member") Member member){
  return orderservice.waitingShippingCount(member);
}


@GetMapping
("/findList")
public List<Order> findList(@RequestParam(name = "member") Member member,@RequestParam(name = "count") Integer count,@RequestParam(name = "filters") List<Filter> filters,@RequestParam(name = "orders") List<com.easyshopping.Order> orders){
  return orderservice.findList(member,count,filters,orders);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return orderservice.find(Object);
}


@GetMapping
("/getSalesAmount")
public BigDecimal getSalesAmount(@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return orderservice.getSalesAmount(beginDate,endDate);
}


@GetMapping
("/getSalesVolume")
public Integer getSalesVolume(@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return orderservice.getSalesVolume(beginDate,endDate);
}


}