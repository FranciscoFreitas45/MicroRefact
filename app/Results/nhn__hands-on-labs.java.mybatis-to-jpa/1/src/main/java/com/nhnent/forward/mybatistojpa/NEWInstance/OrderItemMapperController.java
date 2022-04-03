package com.nhnent.forward.mybatistojpa.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderItemMapperController {

 private OrderItemMapper orderitemmapper;


@GetMapping
("/insertOrderItem")
public int insertOrderItem(@RequestParam(name = "orderItem") OrderItem orderItem){
  return orderitemmapper.insertOrderItem(orderItem);
}


@GetMapping
("/deleteOrderItem")
public int deleteOrderItem(@RequestParam(name = "orderId") Long orderId){
  return orderitemmapper.deleteOrderItem(orderId);
}


}