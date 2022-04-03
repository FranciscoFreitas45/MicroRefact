package com.sprint2.service;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.sprint2.model.Order;
public interface IOrderService {


public Order addContract(Order Order)
;

public List<Order> getAllOrder()
;

public boolean deleteOrderbyorderNumber(Integer orderNumber)
;

public Order updateOrder(Order Order)
;

public Order getOrderByorderNumber(Integer orderNumber)
;

}