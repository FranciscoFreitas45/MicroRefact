package com.yalcin.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.entity.Product;
@RestController
@CrossOrigin
public class ProductShowcaseController {

@Autowired
 private ProductShowcaseService productshowcaseservice;


@PutMapping
("/Showcase/{id}/Product/setProduct")
public void setProduct(@PathVariable(name="id") Integer id,@RequestBody Product product){
productshowcaseservice.setProduct(id,product);
}


@GetMapping
("/Showcase/{id}/Product/getProduct")
public Product getProduct(@PathVariable(name="id") Integer id){
return productshowcaseservice.getProduct(id);
}


}