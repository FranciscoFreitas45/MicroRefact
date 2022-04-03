package com.cg.oms.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.DTO.Order;
import com.cg.oms.Request.OrderRequest;
public class OrderRequestImpl implements OrderRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setOrder(Order order,Long orderId){
 restTemplate.put("http://3/OrderMedicine/{id}/Order/setOrder",order,orderId);
 return ;
}


public Order getOrder(Long orderId){
 Order aux = restTemplate.getForObject("http://3/OrderMedicine/{id}/Order/getOrder",Order.class,orderId);
return aux;
}


}