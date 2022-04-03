package com.nhnent.forward.mybatistojpa.Interface;
public interface OrderItemMapper {

   public int insertOrderItem(OrderItem orderItem);
   public int deleteOrderItem(Long orderId);
}