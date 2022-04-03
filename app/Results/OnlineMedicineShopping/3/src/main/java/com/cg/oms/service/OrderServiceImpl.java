package com.cg.oms.service;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.oms.converter.OrderConverter;
import com.cg.oms.exception.OrderNotFoundException;
import com.cg.oms.model.Order;
import com.cg.oms.repository.OrderRepository;
import com.cg.oms.vo.OrderVo;
@Service
public class OrderServiceImpl implements OrderService{

 public  String EXCEPTION_MESSAGE;

@Autowired
 private  OrderRepository orderRepository;

@Autowired
 private  OrderConverter orderConverter;


@Override
public OrderVo getOrderById(Long id){
    Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(EXCEPTION_MESSAGE + id));
    return orderConverter.modelToVo(order);
}


@Override
public String placeOrder(OrderVo orderVo){
    Order order = orderConverter.voToModel(orderVo);
    order = orderRepository.save(order);
    OrderVo savedVo = orderConverter.modelToVo(order);
    return "Registered SuccessFully!!! " + savedVo.toString();
}


@Override
public String cancelOrder(Long id){
    Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(EXCEPTION_MESSAGE + id));
    orderRepository.delete(order);
    return "Record Deleted Successfully!!";
}


}