package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.DTO.CreateOrderDTO;
import com.project.stockexchangeappbackend.entity.AllOrders;
import com.project.stockexchangeappbackend.entity.*;
import com.project.stockexchangeappbackend.DTO.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
public interface OrderService {


public void createOrder(CreateOrderDTO orderDTO)
;

public Page<AllOrders> getOwnedOrders(Pageable pageable,Specification<AllOrders> specification)
;

public List<Order> getActiveBuyingOrders()
;

public Optional<Order> refreshObjectById(Long id)
;

public Page<AllOrders> findAllOrders(Pageable pageable,Specification<AllOrders> specification)
;

public Page<AllOrders> getOrdersByUser(Pageable pageable,Specification<AllOrders> specification,Long id)
;

public void moveInactiveOrders()
;

public List<Order> getActiveSellingOrdersByStockAndPriceLessThanEqual(Stock stock,BigDecimal maximalPrice)
;

public void deactivateOrder(Long id)
;

public AllOrders findOrderById(Long id)
;

}