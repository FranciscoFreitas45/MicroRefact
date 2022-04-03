package com.easyshopping.dao.impl;
 import com.easyshopping.dao.OrderItemDao;
import com.easyshopping.entity.OrderItem;
import org.springframework.stereotype.Repository;
@Repository("orderItemDaoImpl")
public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem, Long>implements OrderItemDao{


}