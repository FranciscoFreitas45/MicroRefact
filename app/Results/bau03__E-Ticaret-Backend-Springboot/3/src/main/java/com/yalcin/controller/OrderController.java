package com.yalcin.controller;
 import com.yalcin.dto.response.SuccessResponse;
import com.yalcin.entity.Order;
import com.yalcin.entity.Product;
import com.yalcin.entity.User;
import com.yalcin.event.SellerProductSuccessEvent;
import com.yalcin.exception.ErrorWhileSendingEmailException;
import com.yalcin.repository.OrderRepository;
import com.yalcin.repository.ProductRepository;
import com.yalcin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation;
import com.yalcin.Interface.UserServiceImpl;
import com.yalcin.DTO.SuccessResponse;
@RestController
@RequestMapping("ecommerce/order")
public class OrderController {

@Autowired
 private OrderRepository orderRepository;

@Autowired
 private UserServiceImpl userServiceImpl;

@Autowired
 private ProductRepository productRepository;

@Autowired
 private  ApplicationEventPublisher eventPublisher;


@PutMapping("/edit/{orderId}")
public ResponseEntity<?> editOrder(String orderId){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    Order order = orderRepository.findAllById(Integer.parseInt(orderId));
    order.setEnabled(true);
    orderRepository.save(order);
    try {
        eventPublisher.publishEvent(new SellerProductSuccessEvent(user, order));
    } catch (Exception re) {
        throw new ErrorWhileSendingEmailException(re.getMessage());
    }
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Sipariş Onaylandı");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


@GetMapping("/all")
public ResponseEntity<?> getOrder(){
    User user = userServiceImpl.getUserWithAuthentication(SecurityContextHolder.getContext().getAuthentication());
    return ResponseEntity.ok().body(orderRepository.findAllByUser_Id(user.getId()));
}


@DeleteMapping("/delete/{orderId}")
public ResponseEntity<?> deleteOrder(String orderId){
    Order order = orderRepository.findAllById(Integer.parseInt(orderId));
    Product product = order.getProduct();
    product.setStock(product.getStock() + 1);
    productRepository.save(product);
    orderRepository.delete(order);
    SuccessResponse response = new SuccessResponse(HttpStatus.OK, "Sipariş Silindi");
    return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
}


}