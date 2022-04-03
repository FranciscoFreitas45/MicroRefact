package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReturnsDaoController {

 private ReturnsDao returnsdao;


@GetMapping
("/persist")
public Object persist(@RequestParam(name = "Object") Object Object){
  return returnsdao.persist(Object);
}


}