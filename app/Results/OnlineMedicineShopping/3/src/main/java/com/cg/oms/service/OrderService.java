package com.cg.oms.service;
 import com.cg.oms.exception.OrderNotFoundException;
import com.cg.oms.vo.OrderVo;
public interface OrderService {


public OrderVo getOrderById(Long id)
;

public String placeOrder(OrderVo orderVo)
;

public String cancelOrder(Long id)
;

}