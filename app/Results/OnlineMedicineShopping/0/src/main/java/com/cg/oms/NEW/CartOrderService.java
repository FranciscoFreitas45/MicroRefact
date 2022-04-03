package com.cg.oms.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.repository.CartRepository;
import com.cg.oms.model.Cart;
@Service
public class CartOrderService {

@Autowired
 private CartRepository cartrepository;


public void setCart(Long cartId,Cart cart){
cartrepository.setCart(cartId,cart);
}


public Cart getCart(Long cartId){
return cartrepository.getCart(cartId);
}


}