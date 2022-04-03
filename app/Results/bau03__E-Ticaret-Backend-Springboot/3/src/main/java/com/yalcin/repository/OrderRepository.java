package com.yalcin.repository;
 import com.yalcin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer>{


public List<Order> findAllByUser_Id(Integer userId)
;

public Order findAllById(Integer orderId)
;

@Query("select t from Order t join t.product u  where u.user.id = :userId")
public List<Order> findAllByUsername(Integer userId)
;

public void setAdress(Integer id,Adress adress);

public void setEnabled(Integer id,boolean enabled);

public void setTimestamp(Integer id,Date timestamp);

public void setProduct(Integer id,Product product);

}