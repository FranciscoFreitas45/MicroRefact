package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IOrderServiceController {

 private IOrderService iorderservice;


@GetMapping
("/queryOrderList")
public List<Map<String,Object>> queryOrderList(@RequestParam(name = "page") Page page,@RequestParam(name = "userId") int userId){
  return iorderservice.queryOrderList(page,userId);
}


@GetMapping
("/queryOrderDetailList")
public Map<String,List<Map<String,Object>>> queryOrderDetailList(@RequestParam(name = "orderId") String orderId){
  return iorderservice.queryOrderDetailList(orderId);
}


@GetMapping
("/queryMOrderDetailList")
public List<Map<String,Object>> queryMOrderDetailList(@RequestParam(name = "orderId") String orderId){
  return iorderservice.queryMOrderDetailList(orderId);
}


}