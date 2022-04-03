package com.yalcin.controller;
 import com.yalcin.dto.request.OrderForm;
import com.yalcin.dto.request.StoreForm;
import com.yalcin.dto.response.SuccessResponse;
import com.yalcin.entity;
import com.yalcin.repository.ProductRepository;
import com.yalcin.repository.StoreRepository;
import com.yalcin.service.OrderService;
import com.yalcin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.lang.Integer.parseInt;
import com.yalcin.Interface.UserServiceImpl;
import com.yalcin.Interface.ProductRepository;
import com.yalcin.DTO.SuccessResponse;
@RestController
@RequestMapping("ecommerce/store")
public class StoreController {

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private OrderService orderService;

@Autowired
 private ProductRepository productRepository;

@Autowired
 private StoreRepository storeRepository;


@GetMapping("storeDetail")
public ResponseEntity<?> getStore(){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    return ResponseEntity.ok().body(storeRepository.findAllByUser_IdAndEnabled(user.getId(), false));
}


@GetMapping("totalPrice")
public ResponseEntity<?> getTotalPrice(){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    List<Store> store = storeRepository.findAllByUser_IdAndEnabled(user.getId(), false);
    float totalPrice = 0;
    for (int i = store.size() - 1; i >= 0; i--) {
        totalPrice = totalPrice + store.get(i).getProduct().getPrice();
    }
    return ResponseEntity.ok().body(totalPrice);
}


@PostMapping("/order")
public ResponseEntity<?> getUserProduct(OrderForm orderForm){
    orderService.orderSave(orderForm);
    SuccessResponse response = new SuccessResponse(HttpStatus.CREATED, "Sipariş alındı satıcı tarafından onay bekleniyor.");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@PostMapping("add")
public ResponseEntity<?> addStore(StoreForm storeForm){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    Product product = productRepository.findAllById(parseInt(storeForm.getProductId()));
    product.setStock(product.getStock() - 1);
    productRepository.save(product);
    Store store = new Store();
    store.setUser(user);
    store.setProduct(product);
    store.setTimestap(new Date());
    store.setEnabled(false);
    storeRepository.save(store);
    SuccessResponse response = new SuccessResponse(HttpStatus.CREATED, "Ürün sepete eklendi.");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@DeleteMapping("/delete/{storeId}")
public ResponseEntity<?> deleteStore(String storeId){
    Store store = storeRepository.findAllById(parseInt(storeId));
    storeRepository.delete(store);
    Product product = store.getProduct();
    product.setStock(product.getStock() + 1);
    productRepository.save(product);
    SuccessResponse response = new SuccessResponse(HttpStatus.CREATED, "Ürün Sepetten Silindi");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


}