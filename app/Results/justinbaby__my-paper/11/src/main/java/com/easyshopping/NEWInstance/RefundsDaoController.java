package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RefundsDaoController {

 private RefundsDao refundsdao;


@GetMapping
("/persist")
public Object persist(@RequestParam(name = "Object") Object Object){
  return refundsdao.persist(Object);
}


}