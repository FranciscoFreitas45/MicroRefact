package com.example.demo.Dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Report;
public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{


@Query("SELECT o FROM OrderDetail o WHERE o.order.id=:id")
public List<OrderDetail> findByOrderId(long id)
;

@Query("SELECT new Report(d.product.category, sum(d.price*d.quantity)," + "sum(d.quantity)) FROM OrderDetail d GROUP BY d.product.category")
public List<Report> revenueCategory()
;

}