package com.cg.oms.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.model.Order;
@RestController
@CrossOrigin
public class OrderOrderMedicineController {

@Autowired
 private OrderOrderMedicineService orderordermedicineservice;


@PutMapping
("/OrderMedicine/{id}/Order/setOrder")
public void setOrder(@PathVariable(name="id") Long orderId,@RequestBody Order order){
orderordermedicineservice.setOrder(orderId,order);
}


@GetMapping
("/OrderMedicine/{id}/Order/getOrder")
public Order getOrder(@PathVariable(name="id") Long orderId){
return orderordermedicineservice.getOrder(orderId);
}


}