package com.easyshopping.service;
 import com.easyshopping.entity.Cart;
import com.easyshopping.entity.Member;
public interface CartService extends BaseService<Cart, Long>{


public void evictExpired()
;

public void merge(Member member,Cart cart)
;

public Cart getCurrent()
;

}