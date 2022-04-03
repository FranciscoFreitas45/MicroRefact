package com.cg.oms.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.DTO.Cart;
import com.cg.oms.Request.CartRequest;
public class CartRequestImpl implements CartRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setCart(Cart cart,Long cartId){
 restTemplate.put("http://0/Order/{id}/Cart/setCart",cart,cartId);
 return ;
}


public Cart getCart(Long cartId){
 Cart aux = restTemplate.getForObject("http://0/Order/{id}/Cart/getCart",Cart.class,cartId);
return aux;
}


}