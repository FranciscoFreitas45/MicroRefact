package com.weflors.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductServiceImplController {

 private ProductServiceImpl productserviceimpl;


@GetMapping
("/saveProduct")
public ProductEntity saveProduct(@RequestParam(name = "productEntity") ProductEntity productEntity){
  return productserviceimpl.saveProduct(productEntity);
}


}