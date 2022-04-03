package com.sprint2.controller;
 import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Order;
import com.sprint2.service;
@Controller
@RequestMapping("/Order")
public class OrderController {

 private Logger logger;

@Autowired
 private  OrderService OrderService;


@GetMapping("/")
@ResponseBody
public List<Order> getAllOrder(){
    List<Order> Orderlist = OrderService.getAllOrders();
    return Orderlist;
}


@DeleteMapping("/{orderNumber}")
@ResponseBody
public boolean deleteOrderbyorderNumber(Integer orderNumber){
    return OrderService.deleteOrderbyorderNumber(orderNumber);
}


@PostMapping("/")
@ResponseBody
public Order addOrder(Order Order){
    return OrderService.addOrder(Order);
}


@PutMapping("/")
@ResponseBody
public Order updateOrder(Order Order){
    return OrderService.updateOrder(Order);
}


@GetMapping("/{orderNumber}")
@ResponseBody
public Order getOrderByorderNumber(Integer orderNumber){
    logger.info("order service was instalized");
    return OrderService.getOrderByorderNumber(orderNumber);
}


}