package com.easyshopping.dao;
 import com.easyshopping.entity.Cart;
public interface CartDao extends BaseDao<Cart, Long>{


public void evictExpired()
;

}