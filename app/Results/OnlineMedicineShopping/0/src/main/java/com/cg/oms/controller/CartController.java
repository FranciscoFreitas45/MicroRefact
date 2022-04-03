package com.cg.oms.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.oms.exception.CartNotFoundException;
import com.cg.oms.service.CartServiceImpl;
import com.cg.oms.vo.CartVo;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class CartController {

@Autowired
 private  CartServiceImpl cartServiceImpl;


@GetMapping("/cart/getcartbyId/{id}")
public ResponseEntity<CartVo> getCartId(Long cartId)  throws CartNotFoundException{
    CartVo cartVo = cartServiceImpl.getCartById(cartId);
    return ResponseEntity.ok().body(cartVo);
}


@PostMapping("/cart/newcart")
public ResponseEntity<CartVo> addCart(CartVo cartVo)  throws CartNotFoundException{
    cartServiceImpl.saveCart(cartVo);
    return ResponseEntity.ok(cartVo);
}


@GetMapping("/cart/getAll")
public ResponseEntity<List<CartVo>> getAllCarts()  throws CartNotFoundException{
    List<CartVo> cartVo = cartServiceImpl.getAllCarts();
    return ResponseEntity.ok().body(cartVo);
}


@DeleteMapping("/cart/removecart/{cartid}")
public String deleteCart(Long cartId)  throws CartNotFoundException{
    return cartServiceImpl.deleteCart(cartId);
}


}