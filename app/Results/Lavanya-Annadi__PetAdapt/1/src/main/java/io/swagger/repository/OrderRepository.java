package io.swagger.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.HealthCare;
import io.swagger.model.Medic;
import io.swagger.model.Order;
import io.swagger.model.Provider;
import io.swagger.model.User;
@Transactional
public interface OrderRepository extends CrudRepository<Order, Long>, JpaRepository<Order, Long>{


public List<Order> findByUser(User user)
;

public List<Order> findByProvider(Provider provider)
;

}