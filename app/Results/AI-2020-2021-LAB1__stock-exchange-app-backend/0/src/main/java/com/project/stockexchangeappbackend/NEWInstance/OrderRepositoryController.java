package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderRepositoryController {

 private OrderRepository orderrepository;


@GetMapping
("/findByStock")
public List<Order> findByStock(@RequestParam(name = "stock") Stock stock){
  return orderrepository.findByStock(stock);
}


@PutMapping
("/deleteAll")
public void deleteAll(@RequestParam(name = "var1") Iterable<? extends Order> var1){
orderrepository.deleteAll(var1);
}


@GetMapping
("/findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull")
public List<Order> findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(@RequestParam(name = "stock") Stock stock,@RequestParam(name = "user") User user,@RequestParam(name = "orderType") OrderType orderType,@RequestParam(name = "dateExpiration") OffsetDateTime dateExpiration){
  return orderrepository.findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(stock,user,orderType,dateExpiration);
}


}