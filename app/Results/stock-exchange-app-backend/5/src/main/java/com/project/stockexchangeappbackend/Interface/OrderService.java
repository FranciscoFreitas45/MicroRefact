package com.project.stockexchangeappbackend.Interface;
public interface OrderService {

   public Page<AllOrders> getOwnedOrders(Pageable pageable,Specification<AllOrders> specification);
   public Page<AllOrders> getOrdersByUser(Pageable pageable,Specification<AllOrders> specification,Long id);
}