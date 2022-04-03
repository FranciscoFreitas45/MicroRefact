package com.yalcin.Interface;
public interface OrderRepository {

   public List<Order> findAllByUsername(Integer userId);
}