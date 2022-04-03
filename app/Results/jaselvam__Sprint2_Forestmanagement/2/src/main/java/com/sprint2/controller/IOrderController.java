package com.sprint2.controller;
 import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.Order;
public interface IOrderController {


@ResponseBody
public List<Order> getAllOrder()
;

@ResponseBody
public boolean deleteOrderbyorderNumber(Integer orderNumber)
;

@ResponseBody
public Order addOrder(Order Order)
;

@ResponseBody
public Order updateOrder(Order Order)
;

@ResponseBody
public Order getOrderByorderNumber(Integer orderNumber)
;

}