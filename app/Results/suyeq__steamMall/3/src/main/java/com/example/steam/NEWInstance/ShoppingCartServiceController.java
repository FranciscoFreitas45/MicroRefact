package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ShoppingCartServiceController {

 private ShoppingCartService shoppingcartservice;


@GetMapping
("/findAllCart")
public List<ShoppingCartDetail> findAllCart(){
  return shoppingcartservice.findAllCart();
}


}