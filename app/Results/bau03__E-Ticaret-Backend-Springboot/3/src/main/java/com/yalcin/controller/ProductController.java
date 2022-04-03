package com.yalcin.controller;
 import com.yalcin.dto.request.AddProductForm;
import com.yalcin.dto.response.SuccessResponse;
import com.yalcin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
@RestController
@RequestMapping("ecommerce/product")
public class ProductController {

@Autowired
 private ProductService productService;


@GetMapping("/products")
public ResponseEntity<?> getContent(){
    return ResponseEntity.ok().body(productService.getCheckProduct());
}


@PostMapping("add")
public ResponseEntity<?> registerUser(AddProductForm addProductForm){
    productService.productSave(addProductForm);
    SuccessResponse response = new SuccessResponse(HttpStatus.CREATED, "Ürün eklendi.");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


}