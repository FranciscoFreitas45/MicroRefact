package com.cg.oms.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.repository.OrderRepository;
import com.cg.oms.model.Order;
@Service
public class OrderOrderMedicineService {

@Autowired
 private OrderRepository orderrepository;


public void setOrder(Long orderId,Order order){
orderrepository.setOrder(orderId,order);
}


public Order getOrder(Long orderId){
return orderrepository.getOrder(orderId);
}


}