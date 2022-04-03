package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderLogDaoController {

 private OrderLogDao orderlogdao;


@GetMapping
("/persist")
public Object persist(@RequestParam(name = "Object") Object Object){
  return orderlogdao.persist(Object);
}


}