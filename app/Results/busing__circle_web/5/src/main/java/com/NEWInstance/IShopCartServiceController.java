package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IShopCartServiceController {

 private IShopCartService ishopcartservice;


@GetMapping
("/queryShopCartList")
public List<Map<String,Object>> queryShopCartList(@RequestParam(name = "page") Page page,@RequestParam(name = "userId") int userId){
  return ishopcartservice.queryShopCartList(page,userId);
}


@GetMapping
("/queryCalShopCartList")
public Map<String,List<Map<String,Object>>> queryCalShopCartList(@RequestParam(name = "userId") int userId,@RequestParam(name = "calCartId") String calCartId){
  return ishopcartservice.queryCalShopCartList(userId,calCartId);
}


@GetMapping
("/calTotal")
public double calTotal(@RequestParam(name = "cartId") String[] cartId){
  return ishopcartservice.calTotal(cartId);
}


}