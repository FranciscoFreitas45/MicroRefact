package io.delivery.dao;
 import io.delivery.entity.BasketUnit;
import io.delivery.entity.Order;
import java.util.List;
public interface OrderDao extends BasicDao<Order>{


public BasketUnit findBasketUnitById(long id)
;

public List<Order> findByUserId(long uid)
;

public BasketUnit deleteBasketUnitById(BasketUnit basketUnit)
;

}