package com.cg.oms.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.oms.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{


public void setOrder(Long orderId,Order order);

public Order getOrder(Long orderId);

}