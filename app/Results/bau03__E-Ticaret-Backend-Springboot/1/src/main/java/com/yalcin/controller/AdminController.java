package com.yalcin.controller;
 import com.yalcin.dto.request.EditSellerForm;
import com.yalcin.dto.response.SuccessResponse;
import com.yalcin.service.ProductService;
import com.yalcin.service.SellerBeginService;
import com.yalcin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import com.yalcin.Interface.ProductService;
import com.yalcin.Interface.UserServiceImpl;
@RestController
@PreAuthorize("hasRole ('ROLE_ADMIN')")
@RequestMapping("ecommerce/admin")
public class AdminController {

@Autowired
 private SellerBeginService sellerBeginService;

@Autowired
 private ProductService productService;

@Autowired
 private UserServiceImpl userServiceImpl;


@GetMapping("/detail")
public ResponseEntity<?> getAllSeller(){
    return ResponseEntity.ok().body(sellerBeginService.getSeller());
}


@GetMapping("/productView/{productId}")
public ResponseEntity<?> getContent(String productId){
    return ResponseEntity.ok().body(productService.getProduct(productId));
}


@GetMapping("/product")
public ResponseEntity<?> getAllProduct(){
    return ResponseEntity.ok().body(productService.getProduct());
}


@PutMapping("/edit")
public ResponseEntity<?> editUserRole(EditSellerForm editSellerForm){
    sellerBeginService.editUserRole(editSellerForm);
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Kullanıcı rolü güncellendi");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@PutMapping("/edit/product/{productId}")
public ResponseEntity<?> editProduct(String productId){
    productService.productEdit(productId);
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Ürün Yayınlandı");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


}