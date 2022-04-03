package com.example.demo.Interface;
public interface OrderDetailService {

   public OrderDetail save(OrderDetail entity);
   public List<OrderDetail> findByOrderId(long id);
}