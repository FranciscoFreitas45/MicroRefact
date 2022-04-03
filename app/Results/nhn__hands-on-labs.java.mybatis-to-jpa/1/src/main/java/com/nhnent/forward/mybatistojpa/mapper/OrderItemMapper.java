package com.nhnent.forward.mybatistojpa.mapper;
 import com.nhnent.forward.mybatistojpa.model.OrderItem;
public interface OrderItemMapper {


public int deleteOrderItem(Long orderId)
;

public int insertOrderItem(OrderItem orderItem)
;

}