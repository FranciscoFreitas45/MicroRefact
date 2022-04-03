package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.OrderItemDao;
import com.easyshopping.entity.OrderItem;
import com.easyshopping.service.OrderItemService;
import org.springframework.stereotype.Service;
@Service("orderItemServiceImpl")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem, Long>implements OrderItemService{


@Resource(name = "orderItemDaoImpl")
public void setBaseDao(OrderItemDao orderItemDao){
    super.setBaseDao(orderItemDao);
}


}