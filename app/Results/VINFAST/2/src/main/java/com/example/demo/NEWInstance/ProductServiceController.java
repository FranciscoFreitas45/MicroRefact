package com.example.demo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductServiceController {

 private ProductService productservice;


@GetMapping
("/findByIdproduct")
public Product findByIdproduct(@RequestParam(name = "id") Integer id){
  return productservice.findByIdproduct(id);
}


@GetMapping
("/getOne")
public Product getOne(@RequestParam(name = "id") Integer id){
  return productservice.getOne(id);
}


}