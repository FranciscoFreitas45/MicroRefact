package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductRepositoryController {

 private ProductRepository productrepository;


@GetMapping
("/findAllById")
public Product findAllById(@RequestParam(name = "productId") Integer productId){
  return productrepository.findAllById(productId);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return productrepository.save(Object);
}


}