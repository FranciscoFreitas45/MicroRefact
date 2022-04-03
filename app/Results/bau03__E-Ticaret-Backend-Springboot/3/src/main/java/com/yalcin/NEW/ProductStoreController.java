package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.Product;
@RestController
@CrossOrigin
public class ProductStoreController {

@Autowired
 private ProductStoreService productstoreservice;


@PutMapping
("/Store/{id}/Product/setProduct")
public void setProduct(@PathVariable(name="id") Integer id,@RequestBody Product product){
productstoreservice.setProduct(id,product);
}


@GetMapping
("/Store/{id}/Product/getProduct")
public Product getProduct(@PathVariable(name="id") Integer id){
return productstoreservice.getProduct(id);
}


}