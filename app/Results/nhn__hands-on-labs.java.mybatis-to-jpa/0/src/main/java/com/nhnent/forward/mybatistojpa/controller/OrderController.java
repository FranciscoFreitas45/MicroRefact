package com.nhnent.forward.mybatistojpa.controller;
 import com.nhnent.forward.mybatistojpa.model.Order;
import com.nhnent.forward.mybatistojpa.model.Page;
import com.nhnent.forward.mybatistojpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/orders")
public class OrderController {

@Autowired
 private OrderService orderService;


@PostMapping("")
public Order createOrder(Order order){
    return orderService.createOrder(order);
}


@GetMapping("/{orderId}")
public Order getOrder(Long orderId){
    return orderService.getOrder(orderId);
}


@DeleteMapping("/{orderId}")
public void deleteOrder(Long orderId){
    orderService.deleteOrder(orderId);
}


@GetMapping("")
public List<Order> getOrders(int page){
    if (page < 1) {
        page = 1;
    }
    return orderService.getOrders(page, Page.PAGE_SIZE);
}


}