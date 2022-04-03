package com.cg.oms.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.model.Cart;
@RestController
@CrossOrigin
public class CartOrderController {

@Autowired
 private CartOrderService cartorderservice;


@PutMapping
("/Order/{id}/Cart/setCart")
public void setCart(@PathVariable(name="id") Long cartId,@RequestBody Cart cart){
cartorderservice.setCart(cartId,cart);
}


@GetMapping
("/Order/{id}/Cart/getCart")
public Cart getCart(@PathVariable(name="id") Long cartId){
return cartorderservice.getCart(cartId);
}


}