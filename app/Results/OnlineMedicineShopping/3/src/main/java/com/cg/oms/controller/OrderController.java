package com.cg.oms.controller;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.oms.exception.OrderNotFoundException;
import com.cg.oms.service.OrderServiceImpl;
import com.cg.oms.vo.OrderVo;
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
@RestController
public class OrderController {

@Autowired
 private  OrderServiceImpl orderServiceImpl;


@GetMapping("/order/order/{id}")
public ResponseEntity<OrderVo> viewOrder(Long orderId){
    return ResponseEntity.ok().body(orderServiceImpl.getOrderById(orderId));
}


@PostMapping("/order/placeOrder")
public ResponseEntity<OrderVo> placeOrder(OrderVo orderVo){
    orderServiceImpl.placeOrder(orderVo);
    return ResponseEntity.ok(orderVo);
}


@GetMapping("/order/cancelOrder/{id}")
public String cancelOrder(Long id){
    return orderServiceImpl.cancelOrder(id);
}


}