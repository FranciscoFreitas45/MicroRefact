package io.delivery.service;
 import io.delivery.entity.BasketUnit;
import io.delivery.entity.Order;
import java.util.List;
public interface OrderService {


public List<Order> getOrderList()
;

public BasketUnit findBasketUnitById(long id)
;

public Order findById(long id)
;

public List<Order> findByUserId(long uid)
;

public Order create(Order order)
;

public Order updateOrder(Order order)
;

public BasketUnit deleteBasketUnitById(long id)
;

public Order deleteOrder(long id)
;

}