package com.project.stockexchangeappbackend.Interface;
public interface OrderRepository {

   public List<Order> findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(Stock stock,User user,OrderType orderType,OffsetDateTime dateExpiration);
}