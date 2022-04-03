package com.easyshopping.service.impl;
 import javax.annotation.Resource;
import com.easyshopping.dao.OrderLogDao;
import com.easyshopping.entity.OrderLog;
import com.easyshopping.service.OrderLogService;
import org.springframework.stereotype.Service;
@Service("orderLogServiceImpl")
public class OrderLogServiceImpl extends BaseServiceImpl<OrderLog, Long>implements OrderLogService{


@Resource(name = "orderLogDaoImpl")
public void setBaseDao(OrderLogDao orderLogDao){
    super.setBaseDao(orderLogDao);
}


}