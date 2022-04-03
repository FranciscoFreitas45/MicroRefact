package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IShopCartDAOController {

 private IShopCartDAO ishopcartdao;


@GetMapping
("/deleteShopCart")
public boolean deleteShopCart(@RequestParam(name = "cartId") String cartId){
  return ishopcartdao.deleteShopCart(cartId);
}


}