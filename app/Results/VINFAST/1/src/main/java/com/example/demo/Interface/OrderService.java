package com.example.demo.Interface;
public interface OrderService {

   public Order save(Order entity);
   public List<Order> findByUsername(String username);
   public Order getOne(Long id);
}