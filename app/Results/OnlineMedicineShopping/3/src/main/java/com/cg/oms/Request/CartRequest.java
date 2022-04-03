package com.cg.oms.Request;
import com.cg.oms.DTO.Cart;
public interface CartRequest {

   public void setCart(Cart cart,Long cartId);
   public Cart getCart(Long cartId);
}