package com.easyshopping.dao.impl;
 import com.easyshopping.dao.CartItemDao;
import com.easyshopping.entity.CartItem;
import org.springframework.stereotype.Repository;
@Repository("cartItemDaoImpl")
public class CartItemDaoImpl extends BaseDaoImpl<CartItem, Long>implements CartItemDao{


}