package com.cg.oms.Request;
import com.cg.oms.DTO.Order;
public interface OrderRequest {

   public void setOrder(Order order,Long orderId);
   public Order getOrder(Long orderId);
}