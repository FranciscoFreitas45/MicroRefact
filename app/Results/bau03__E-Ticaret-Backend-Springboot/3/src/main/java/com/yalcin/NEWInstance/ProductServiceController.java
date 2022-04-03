package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductServiceController {

 private ProductService productservice;


@GetMapping
("/getProduct")
public Product getProduct(@RequestParam(name = "productId") String productId){
  return productservice.getProduct(productId);
}


@PutMapping
("/productEdit")
public void productEdit(@RequestParam(name = "productId") String productId){
productservice.productEdit(productId);
}


@GetMapping
("/getUserProduct")
public List<Product> getUserProduct(@RequestParam(name = "userId") String userId){
  return productservice.getUserProduct(userId);
}


}