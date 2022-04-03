package com.zis.trade.processor;
 import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.trade.entity.Order;
public interface OrderProcessor {


public Order createOrder(CreateTradeOrderDTO orderDTO)
;

}