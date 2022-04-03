package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductImageServiceController {

 private ProductImageService productimageservice;


@PutMapping
("/build")
public void build(@RequestParam(name = "productImage") ProductImage productImage){
productimageservice.build(productImage);
}


}