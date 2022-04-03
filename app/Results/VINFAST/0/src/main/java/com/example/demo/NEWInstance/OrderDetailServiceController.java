package com.example.demo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderDetailServiceController {

 private OrderDetailService orderdetailservice;


@GetMapping
("/save")
public OrderDetail save(@RequestParam(name = "entity") OrderDetail entity){
  return orderdetailservice.save(entity);
}


@GetMapping
("/findByOrderId")
public List<OrderDetail> findByOrderId(@RequestParam(name = "id") long id){
  return orderdetailservice.findByOrderId(id);
}


}