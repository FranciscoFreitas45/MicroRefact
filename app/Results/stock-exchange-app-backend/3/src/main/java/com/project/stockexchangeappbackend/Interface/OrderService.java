package com.project.stockexchangeappbackend.Interface;
public interface OrderService {

   public AllOrders findOrderById(Long id);
   public void createOrder(CreateOrderDTO orderDTO);
   public Page<AllOrders> findAllOrders(Pageable pageable,Specification<AllOrders> specification);
   public void deactivateOrder(Long id);
}