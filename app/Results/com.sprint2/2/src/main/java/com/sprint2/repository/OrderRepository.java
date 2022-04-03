package com.sprint2.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint2.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{


}