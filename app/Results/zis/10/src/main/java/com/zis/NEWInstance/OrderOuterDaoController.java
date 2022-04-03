package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderOuterDaoController {

 private OrderOuterDao orderouterdao;


@GetMapping
("/findByOrderGroupNumber")
public List<OrderOuter> findByOrderGroupNumber(@RequestParam(name = "orderGroupNumber") String orderGroupNumber){
  return orderouterdao.findByOrderGroupNumber(orderGroupNumber);
}


}