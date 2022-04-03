package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CartDaoController {

 private CartDao cartdao;


@GetMapping
("/remove")
public Object remove(@RequestParam(name = "Object") Object Object){
  return cartdao.remove(Object);
}


}