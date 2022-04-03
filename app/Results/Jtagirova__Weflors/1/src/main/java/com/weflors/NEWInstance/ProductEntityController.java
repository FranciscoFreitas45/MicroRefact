package com.weflors.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductEntityController {

 private ProductEntity productentity;

 private ProductEntity productentity;


@PutMapping
("/setProductDetailsByProductId")
public void setProductDetailsByProductId(@RequestParam(name = "productDetailsByProductId") ProductDetailsEntity productDetailsByProductId){
productentity.setProductDetailsByProductId(productDetailsByProductId);
}


}