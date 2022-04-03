package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CartServiceController {

 private CartService cartservice;


@GetMapping
("/getCurrent")
public Cart getCurrent(){
  return cartservice.getCurrent();
}


@PutMapping
("/merge")
public void merge(@RequestParam(name = "member") Member member,@RequestParam(name = "cart") Cart cart){
cartservice.merge(member,cart);
}


}