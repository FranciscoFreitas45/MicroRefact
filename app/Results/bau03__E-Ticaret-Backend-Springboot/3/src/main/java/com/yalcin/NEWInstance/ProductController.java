package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProductController {

 private Product product;


@PutMapping
("/setShowcaseEnabled/{id}")
public void setShowcaseEnabled(@PathVariable(name = "id") Integer id,@RequestParam(name = "showcaseEnabled") boolean showcaseEnabled){
 productrepository.setShowcaseEnabled(id,showcaseEnabled);
}


@PutMapping
("/setStock/{id}")
public void setStock(@PathVariable(name = "id") Integer id,@RequestParam(name = "stock") Integer stock){
 productrepository.setStock(id,stock);
}


}