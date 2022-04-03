package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderServiceController {

 private OrderService orderservice;


@GetMapping
("/getOwnedOrders")
public Page<AllOrders> getOwnedOrders(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<AllOrders> specification){
  return orderservice.getOwnedOrders(pageable,specification);
}


@GetMapping
("/getOrdersByUser")
public Page<AllOrders> getOrdersByUser(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<AllOrders> specification,@RequestParam(name = "id") Long id){
  return orderservice.getOrdersByUser(pageable,specification,id);
}


@GetMapping
("/findOrderById")
public AllOrders findOrderById(@RequestParam(name = "id") Long id){
  return orderservice.findOrderById(id);
}


@PutMapping
("/createOrder")
public void createOrder(@RequestParam(name = "orderDTO") CreateOrderDTO orderDTO){
orderservice.createOrder(orderDTO);
}


@GetMapping
("/findAllOrders")
public Page<AllOrders> findAllOrders(@RequestParam(name = "pageable") Pageable pageable,@RequestParam(name = "specification") Specification<AllOrders> specification){
  return orderservice.findAllOrders(pageable,specification);
}


@PutMapping
("/deactivateOrder")
public void deactivateOrder(@RequestParam(name = "id") Long id){
orderservice.deactivateOrder(id);
}


}