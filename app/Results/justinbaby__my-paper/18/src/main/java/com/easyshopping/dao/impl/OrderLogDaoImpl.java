package com.easyshopping.dao.impl;
 import com.easyshopping.dao.OrderLogDao;
import com.easyshopping.entity.OrderLog;
import org.springframework.stereotype.Repository;
@Repository("orderLogDaoImpl")
public class OrderLogDaoImpl extends BaseDaoImpl<OrderLog, Long>implements OrderLogDao{


}