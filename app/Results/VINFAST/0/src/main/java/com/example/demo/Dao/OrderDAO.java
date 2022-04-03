package com.example.demo.Dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entity.Order;
public interface OrderDAO extends JpaRepository<Order, Long>{


@Query("SELECT u FROM Order u WHERE u.status=:status")
public List<Order> findByStatus(int status)
;

@Query("SELECT u FROM Order u WHERE u.users.username=:username")
public List<Order> findByUsername(String username)
;

}