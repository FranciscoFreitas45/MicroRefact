package com.project.stockexchangeappbackend.Interface;
import com.project.stockexchangeappbackend.Interface.OrderRepository;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.Stock;
import com.project.stockexchangeappbackend.DTO.Order;
import com.project.stockexchangeappbackend.DTO.OrderType;
import com.project.stockexchangeappbackend.DTO.User;
import java.time.*;
public interface OrderRepository {

   public List<Order> findByStockAndUserAndOrderTypeAndDateExpirationIsAfterAndDateClosingIsNull(Stock stock,User user,OrderType orderType,OffsetDateTime dateExpiration);
}